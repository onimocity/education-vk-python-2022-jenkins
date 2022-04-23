#!/bin/bash

cd code


cmd="pytest -s -l -v ${TESTS_PATH} -n ${THREADS:-2} --alluredir /tmp/allure"

if [ -n "${KEYWORD}" ]; then
    cmd="$cmd -k ${KEYWORD}"
fi

${cmd}
