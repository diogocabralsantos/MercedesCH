In the Past MercedesChallenge you will find
the automation project in src
and the manual tasks in TASK_2.

Sorry for the format be the best in the bug report PDF.

For the automation project
TECH STACK:
- JAVA
- Maven
- Selenium
- Cucumber
- TestNG
- Log4j
- DriverManager

My goal with this framework was to show what i value the most in a framework.
A good automation framework for me needs to be modular, functional maintanable in the long run

I used Page object Model with Page Factory, to keep the logic of the business separeted from the elements.
Normally after this i try to identify common functions that will be performed multiple times and separate them in utils classes
or whatever makes sense in the project. Because i´m always trying to follow the DRY principle and a class should only be responsible for one thing.

I used Cucumber because i like a framework where all of the people involved in the project that are non-tech can understand easily what is being done.
Used a logging system and probably is logging everything in to much detail. In a real world scenario i would still use it because it´s very good for debug
and using with for example a Elastic Search.

##Next Improvements for the Framework
- Integrate the automation framework in a CI/CD
- Finish the Paralell testing with more than one browser(i already had all of the logic developed and i left it in the code but i needed more time to figure it out some things to work properly with the framework developed or to change for example for a cloud remote setup)
- Remove the last forced waits(Thread.sleep) i only used it in the parts of the website where i needed to wait for shadow dom. I needed more time to figure it out a way to wait for them.
- Use a more detailed report like Allure or Extent Reports
- Do more Assertions. I tried to spend more time in creating a robust framework and scalable and then do some assertions as final steps