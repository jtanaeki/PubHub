# PubHub 🕮 <img align="right" width="300" height="200" src="WebContent/resources/imgs/ebook3.jpg">
### Book Publishing System
<br>
<a href="CODE_OF_CONDUCT.md">
    <img alt="Contributor Covenant" src="https://img.shields.io/badge/Contributor%20Covenant-2.0-4baaaa.svg" />
</a>

---

A self-publishing platform that allows anyone to write, publish, and sell their own books.

## Setting Up Local Environment
The following instructions will help you set up a local development environment, run the project on your machine for development and testing purposes, and deploy it on a live system. See [*Deployment*](https://github.com/jtanaeki/PubHub/blob/main/README.md#deployment) for notes on how to deploy the project. See [*How to Discuss and Contribute*](https://github.com/jtanaeki/PubHub#how-to-discuss-and-contribute) for notes on how to contribute to this repository.

### Prerequisites
Before building and testing the application, you must make sure to have the following installed:
- The [latest version of Java Runtime Environment (JRE)](https://www.oracle.com/java/technologies/javase-jre8-downloads.html) (Using JRE 1.8, rather than JDK, maybe more suitable for this application to run on WildFly)
- Any IDE that supports JRE development, such as the [latest version of Eclipse](https://www.eclipse.org/downloads/packages/) (Download and install Eclipse IDE for Enterprise Java and Web Developers)
- Application Server, such as the [latest version of WildFly](https://www.wildfly.org/downloads/) (Please read [*Java Development Environment Setup*](Java%Development%Environment%Setup.pdf) to see how to integrate Wildfly with Eclipse)
- Database, such as the [latest version of PostgreSQL](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)

For further instruction on setting up your environment for Java development, read [*Java Development Environment Setup*](Java%Development%Environment%Setup.pdf).

\* *Please keep in mind that these instructions were constructed around 2016.*

### Cloning the Repository
After installing the necessary software, you can clone a local copy of the repository. A [ZIP file version](https://github.com/jtanaeki/PubHub/archive/refs/heads/main.zip) of the copy can be downloaded, or you can clone the repository directly over HTTPS from the command line:

```bash
git clone https://github.com/jtanaeki/PubHub.git
```

### Tables in Database
![Image of Books Table](database_files/books-table.png)

![Image of Books_Tags Table](database_files/books_tags-table.png)

## Running the Application
The following instructions will help you get the program running via Eclipse.

### Importing the Project

- The Import function can be accessed in three ways:
  - The most convenient and quickest way: **File > Import…** (shortcut: **Alt + F + I**):

       ![image](https://user-images.githubusercontent.com/55217672/118418913-5e5ea300-b688-11eb-8ce7-314bf4d58332.png)

  - Right click on any blank space in *Package Explorer* view, select **Import…** from the context menu:

       ![image](https://user-images.githubusercontent.com/55217672/118419010-c90fde80-b688-11eb-8b66-e87a79240128.png)

  - Right click on any blank space in Project Explorer view, then select **Import > Import…** from the context menu:

       ![image](https://user-images.githubusercontent.com/55217672/118419021-d4fba080-b688-11eb-9536-fd28a18a5957.png)

- The **Import** wizard should open:

    ![image](https://user-images.githubusercontent.com/55217672/118419042-e5ac1680-b688-11eb-8fc4-791adebada69.png)

- Under the *General* folder, select *Existing Projects into Workspace* and click **Next**. The next screen, **Import Projects**, appears.       

- If the application was downloaded as a zip file, click **Select archive file** and click the **Browse** button to locate the zip file. Make sure the project is selected and click **Finish**:

    ![image](https://user-images.githubusercontent.com/55217672/118419097-0c6a4d00-b689-11eb-879d-4f1f29e1af4d.png)

- If the application was NOT downloaded as a zip file, click **Select root directory** and click the **Browse** button to find the project’s directory path. Make sure the project and the *Copy projects into workspace* option is selected:

    ![image](https://user-images.githubusercontent.com/55217672/118419200-610dc800-b689-11eb-8629-ee35afe1a925.png)

- The archive will be extracted and the files will be copied into the workspace. The imported project should show in the *Project Explorer/Package Explorer* view:

    ![image](https://user-images.githubusercontent.com/55217672/118419242-6ec34d80-b689-11eb-8fb6-32f9cb417273.png)

## Deployment
### Adding JRE System Library to Java Build Path
If you don't see the JRE System Library with JRE 1.8 in the Java Build Path, under the project name, in the Package Explorer tab, then it must be added.

(IMAGES IN PUBHUB_SCREENSHOTS FOLDER ON DESKTOP)
- Right-click the project folder and click *Properties*

       (IMAGE)

- Click *Java Build Path* in the left-hand side of the dialog box, then click the *Libraries* tab

       (IMAGE)
  
### Integrating the Application Server (Wildfly) with the IDE (Eclipse)
If you have not done so yet, make sure Wildfly is set up on Eclipse for deployment. Please read [*Java Development Environment Setup*](Java%Development%Environment%Setup.pdf). Follow the instructions in the *Integrating Your Application Server with Your IDE (JBoss Middleware)* section.

### Setting up the Database
Please read [*Java Development Environment Setup*](Java%Development%Environment%Setup.pdf) to see how to set up PostgreSQL. Follow the instructions in the *Installing a Database (PostgreSQL)* section. Create [these tables](https://github.com/jtanaeki/PubHub#tables-in-database) in the database and fill in random values.
