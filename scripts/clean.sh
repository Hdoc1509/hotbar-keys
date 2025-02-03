#!/bin/bash

_repo_root=$(git rev-parse --show-toplevel)

# folders
rm -r "$_repo_root"/{Common,Fabric}/bin
rm -r "$_repo_root"/{.,Common,Fabric}/.settings
rm -r "$_repo_root"/{.,buildSrc,Fabric}/.gradle
rm -r "$_repo_root"/{buildSrc,Common,Fabric}/build

# files
rm "$_repo_root"/{Common,Fabric}/.factorypath
rm "$_repo_root"/{Common,Fabric}/.classpath
rm "$_repo_root"/{.,Common,Fabric}/.project
