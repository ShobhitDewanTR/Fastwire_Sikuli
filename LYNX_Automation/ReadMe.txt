	Pre-requisite (Softwares Installs):
	
	1. Eclipse (Any Latest version- 2022-06 (4.24.0))
	2. Java- Amazon Corretto (18.0.2.9 version)
	
	Knowledge required:
	
	1. Basic knowledge of Java
	2. TestNG
	3. Sikuli
	
	Additional Libraries:
	
	No such additional libraries are needed.TestNG and Maven will download necessary jars.
	All will auto import once you clean the project (pom.xml have the required libraries already)
	
	Configuration Steps:
	
	1) Install Amazon Corretto Java on to your machine
	2) Install Eclipse IDE using Amazon Corretto Java as the JRE Environment
	3) Open the Lynx Project In Eclipse IDE
		
		Lynx Framework Structure:
		
					src/main/java->LYNXBase.java-
							All framework objects (screen, reports etc.) are declared, initialized here.

					src/test/java->MainRunner.java
							This is the main runner class with a main function to begin execution and prepare a runtime testing.xml file based on entries from test data sheet.

					src/test/java-> Other java files
							The other java files contains code for individual test cases/scenarios

					src/test/resources->TestData
							Contains Test data sheet and LYNX. Properties file having references to stored object images

					src/test/resources->TestImages
							Contains folder wise object images corresponding to different sections of screen

	Steps to run the Lynx Project
	
	1) Open The Lynx Project in Eclipse.(Click File-> Open Projects from File System -> Select Lynx_Automation project)
	2) Open  the Test_Data.xlsx file, and change the RunTest column value to YES for the test cases which needs to be executed.Save the file.
	3) Open the MainRunner.Java File
	4) Click On Run
	5) Upon Successfull Execution, 'LYNX_Automation_Report YYYY-MM-DD.html'  file will be generated in Lynx_Automation Folder. This file contains the automation run results with Pass Fail status on Test Case and Step level. YYYY is the year, MM is the month, DD is the date of execution.
	
	Note:
	This POC is done for LYNX Fastwire desktop application.
	It may not run directly in your PC. Please check the code flow and create your own test cases.
	
	Author:
	Name: Shobhit Dewan
	Email: Shobit.dewan@thomsonreuters.com
