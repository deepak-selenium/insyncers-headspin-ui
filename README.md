Automation Framework for Headspin hackathon:
---------------------------------------------

1. There are two run modes in this framework i.e local || cloud

2. These settings can be done in "project.properties" file with respect to below field
        
        test.mode.cloud          =  false <true to run in headspin cloud>
        
3. To build and run this framework run command "mvn clean install"

4. For now it runs only in chrome > 78.[Replace the chromedriver file inside 
   folder "/headspin/src/main/java/org/headspin/resources"]

5. For reporting we are using Allure Report - http://allure.qatools.ru/
