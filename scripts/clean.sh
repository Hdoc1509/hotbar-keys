#!/bin/bash

_repo_root=$(git rev-parse --show-toplevel)

# folders
rm -r "$_repo_root"/{common,fabric}/bin
rm -r "$_repo_root"/{.,common,fabric}/.settings
rm -r "$_repo_root"/{.,buildSrc,fabric}/.gradle
rm -r "$_repo_root"/{buildSrc,common,fabric}/build

# files
rm "$_repo_root"/{common,fabric}/.factorypath
rm "$_repo_root"/{common,fabric}/.classpath
rm "$_repo_root"/{.,common,fabric}/.project
