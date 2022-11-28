#!/usr/bin/env bash
export PATH="$PATH:/usr/local/bin/"

cd code

pytest -s -l -v "${TESTS_PATH}" -n "${THREADS:-2}" --alluredir /tmp/allure
