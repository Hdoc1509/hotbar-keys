tag=$1
loader=$2

if [[ -z "$tag" ]]; then
  echo "You must provide a tag name as the first argument"
  exit 1
fi

if [[ -z "$loader" ]]; then
  echo "You must provide a loader name as the second argument"
  exit 1
fi

link_label="CHANGELOG.md"
link_url="https://github.com/Hdoc1509/hotbar-keys/blob/$tag/$loader/CHANGELOG.md"

echo "Please refer to [$link_label]($link_url) for details."
