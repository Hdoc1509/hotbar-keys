breaking_changes_message_file=$REPO_ROOT/scripts/breaking-change-message.tmpl.md
gradle_properties_file="$REPO_ROOT"/gradle.properties

set_changelog_breaking_changes_message() {
  local loader=$1
  local compatible_semver=$2
  local previous_version=$3
  local breaking_changes_file="$REPO_ROOT"/"$loader"/CHANGELOG.md
  local previous_major
  local previous_minor

  previous_major=$(cut --delimiter=. --fields=1 <<<"$previous_version")

  if [[ $compatible_semver == "patch" ]]; then
    previous_minor=$(cut --delimiter=. --fields=2 <<<"$previous_version")
  elif [[ $compatible_semver == "minor" ]]; then
    previous_minor="x"
  else
    echo "Invalid compatible semver: $compatible_semver"
    exit 1
  fi

  echo
  echo "[RELEASE]: Breaking changes detected!"
  echo "[RELEASE]: Generating breaking change message..."

  sed --in-place "4r $breaking_changes_message_file" "$breaking_changes_file"
  sed --in-place \
    --expression="s/{{ compatible_semver }}/$compatible_semver/" \
    --expression="s/{{ previous_version }}/$previous_version/" \
    --expression="s/{{ previous_minor }}/$previous_minor/" \
    --expression="s/{{ previous_major }}/$previous_major/" \
    "$breaking_changes_file"

  echo "[RELEASE]: Breaking changes message generated!"
}

update_gradle_version() {
  local loader=$1
  local new_version=$2
  local field="${loader}_mod_version"

  echo -e "\n[RELEASE $loader]: Updating version in gradle.properties..."

  sed --in-place "s/^$field=.*/$field=$new_version/" "$gradle_properties_file"

  echo -e "[RELEASE $loader]: Version updated"'!'"\n"
  echo "[RELEASE]: If all changes are correct, generate git tags by running:"
  echo "\$ pnpm changeset tag"
  echo "[RELEASE]: Then, update pnpm lock file by running:"
  echo "\$ pnpm install"
}

# NOTE: this can be used to publish and upload .jar files manually
update_jar_version() {
  local loader=$1
  local new_version=$2
  local folder="$REPO_ROOT/$loader/build/libs"

  mv "$folder/hotbar-keys-$loader-1.21-0.0.0.jar" \
    "$folder/hotbar-keys-$loader-1.21-$new_version.jar"
}
