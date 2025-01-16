#!/bin/bash

COMPONENT_KEY='efrancodelima_application'

create_sonar_url() {
  local metric_key="$1"
  curl --location "https://sonarcloud.io/api/measures/component?metricKeys=${metric_key}&component=${COMPONENT_KEY}" \
  --header "Authorization: Bearer ${SONAR_TOKEN}" 2>/dev/null
}

remove_non_numeric() {
  local input_string="$1"
  local numeric_string=$(echo "$input_string" | tr -cd '[:digit:]')
  echo "$numeric_string"
}

echo ""
echo "Code analysis"

# New vulnerabilities
new_vulnerabilities=$(create_sonar_url "new_vulnerabilities" | jq .component.measures[0].periods[0].value)
new_vulnerabilities=$(remove_non_numeric $new_vulnerabilities)

if [ $new_vulnerabilities -gt 0 ]; then
  echo "New vulnerabilities: ${new_vulnerabilities} (fail)"
  exit 1
else
  echo "New vulnerabilities: ${new_vulnerabilities} (ok)"
fi

# New security hotspots
new_sec_hotspots=$(create_sonar_url "new_security_hotspots" | jq .component.measures[0].periods[0].value)
new_sec_hotspots=$(remove_non_numeric $new_sec_hotspots)

if [ $new_sec_hotspots -gt 0 ]; then
  echo "New security hotspots: ${new_sec_hotspots} (fail)"
  exit 1
else
  echo "New security hotspots: ${new_sec_hotspots} (ok)"
fi