# Test task AlphaSense Project

### Setup project:
execute ```mvn clean install```

### Execute tests:
#### Execute commands:
1. run test ```mvn clean test -DsuiteXmlFile=${xml} -Dgroups=${} -Denv=${env}```
2. get report ```mvn allure:serve```
#### Execute by default - ```mvn clean test```
#### Get report manually:
 Click maven ->  Plugins -> allure -> allure:serve