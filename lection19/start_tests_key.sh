#!/usr/bin/env bash
export PATH="$PATH:/usr/local/bin/"

cd code


cmd="pytest -s -l -v ${TESTS_PATH} -n ${THREADS:-2} --alluredir ${WORKSPACE}/alluredir"

if [ -n "${KEYWORD}" ]; then
    cmd="$cmd -k ${KEYWORD}"
fi

${cmd}
