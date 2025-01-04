REPO_ROOT=$(git rev-parse --show-toplevel)
TMP_RELEASE_FILE="$REPO_ROOT"/tmp/breaking-changes.json
UNSTABLE_MESSAGE_REGEX='^\w+(\(.*\))?:!'
loaders=(fabric) # loaders that are developed in this branch
loaders_to_release=()
loaders_with_breaking_changes=()

export REPO_ROOT

# shellcheck disable=SC1091
source "$REPO_ROOT"/scripts/utils.sh

get_loader_last_tag() { git describe --tags --abbrev=0 --match "${1}-*"; }

if [[ ! -d "$REPO_ROOT"/tmp ]]; then
  mkdir "$REPO_ROOT"/tmp
fi

echo "{}" >"$TMP_RELEASE_FILE"

for loader in "${loaders[@]}"; do
  if ! git diff --quiet "$REPO_ROOT/$loader/CHANGELOG.md" &>/dev/null; then
    continue
  fi

  loaders_to_release+=("$loader")
  _breaking_changes_count=$(
    git log "$(get_loader_last_tag "$loader")"..HEAD --oneline |
      grep --count --extended-regexp "$UNSTABLE_MESSAGE_REGEX"
  )

  if [[ $_breaking_changes_count -eq 0 ]]; then
    continue
  fi

  loaders_with_breaking_changes+=("$loader")
  _previous_version=$(jq -r '.version' "$REPO_ROOT"/"$loader"/package.json)

  _tmpfile=$(mktemp)
  cp "$TMP_RELEASE_FILE" "$_tmpfile" &&
    jq ".$loader.previousVersion = \"$_previous_version\"" "$_tmpfile" >"$TMP_RELEASE_FILE" &&
    rm -f -- "$_tmpfile"
done

echo -e "[RELEASE]: Major 0 release"'!'"\n"

# genrate changelogs and update versions
pnpm changeset version

for loader in "${loaders_with_breaking_changes[@]}"; do
  _previous_version=$(jq -r ".$loader.previousVersion" "$TMP_RELEASE_FILE")
  set_breaking_changes_message "$loader" "patch" "$_previous_version"
done

for loader in "${loaders_to_release[@]}"; do
  _new_version=$(jq -r '.version' "$REPO_ROOT/$loader/package.json")
  update_gradle_version "$loader" "$_new_version"
done
