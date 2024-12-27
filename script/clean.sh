_repo_root=$(git rev-parse --show-toplevel)

# folders
rm -r "$_repo_root"/**/bin
rm -r "$_repo_root"/**/.settings
rm -r "$_repo_root"/**/.gradle
rm -r "$_repo_root"/**/build

# files
rm "$_repo_root"/**/.factorypath
rm "$_repo_root"/**/.classpath
rm "$_repo_root"/**/.project
