**Team 105 – PlagiarismCheck application**

**Team members:**

Naga Sai Anirudh Upadhyayula – [upadhyayula.n@husky.neu.edu](mailto:upadhyayula.n@husky.neu.edu)
Nirupama Sharma – [sharma.nir@husky.neu.edu](mailto:sharma.nir@husky.neu.edu)
Aunsh Chaudhari – [chaudhari.au@husky.neu.edu](mailto:chaudhari.au@husky.neu.edu)

**Links to YouTube videos:**

1. [Team-105 Presentation](https://youtu.be/a5e-vaMX2oo)
2. [Team-105 System Demo](https://youtu.be/un2oRDon52I)
3. [Team-105 System Setup](https://youtu.be/J-WCqWUBqu4)

**Link to live system**
[Link to live system](http://checkplagiarism.us-east-2.elasticbeanstalk.com/)

**Prerequisites for running our application:**

1. Java, preferably jdk1.8
2. Apache Maven, preferably Maven 3.5.2
3. Apache Tomcat server, preferably apache-tomcat-9.0.4 or above

Please follow the below steps to be able to run our application on localhost:

1. Copy the contents of the **candidate** branch as directed in the homework expectations document.
2. Once all the software&#39;s needed to run the application are set up on your machine, open eclipse.
3. Select **File -&gt; Import -&gt; Maven -&gt; Existing Maven Projects**
4. In the corresponding window, click on **Browse** and point to the folder **cs5500-sp2018-team-105** from the directory you just downloaded/cloned.
5. Select **Finish** and the project would then be imported on your workspace.

Once the project has been imported into the workspace, you might find a few errors, the root folder of the project would have a red marker. In that case, please follow the set of instructions below to remove those errors and set project up for testing.

1. Refresh the project. If it doesn&#39;t solve the problem, proceed to the next step.
2. Right click on the project and select **Build Path -&gt; Configure Build Path.** Once the new window opens, select **Source** from the tab above and try to find the erroneous addition to the build path. If there exists a /test folder with a red marker on it, please select it and click on remove from the options on the right. This should essentially remove all the errors from the project.
3. If modifying the build path doesn&#39;t solve the problem, right click on the project and select **Run As -&gt; Maven install.** This step will build the project again and remove any potential errors.
4. If the error persists, delete the project from the workspace again and follow the above steps. The error should ideally be removed by now.
5. If Step 4 doesn&#39;t work, delete the project from both the workspace and local repo and try cloning the repository again and repeat the above steps. Let&#39;s hope performing this step is not necessary.

After setting up the project folder on eclipse, the next step would be to be able to run the project on localhost. We&#39;d be needing tomcat server for the following series of steps. Also do remember to run the project in Java EE perspective.

1. Create a new tomcat server on your workspace, if one doesn&#39;t exist already.
2. If a server doesn&#39;t already exist, select the **Servers** tab at the bottom and click on **Create a new Server.** A new window opens which prompts you to select the tomcat directory. Tomcat when installed would ideally be in the Program Files folder (on Windows). Browse to the location and select the tomcat folder. Keep hitting Next until you find Finish and the server then gets created.
3. Once the server is created, right click on the server and add the current project (deployment descriptor) and start the server.
4. Please make sure no other process is running on the 8080 port. If so, be sure to kill that process and try starting the server again.
5. Once the server starts, open the browser of your choice and launch the application using the below URL.

[http://localhost:8080/cs5500-sp2018-team105/login.jsp](http://localhost:8080/cs5500-sp2018-team105/login.jsp)

or

[http://localhost:8080/cs5500-sp2018-team105/](http://localhost:8080/cs5500-sp2018-team105/)

Once the login page loads, the application is all yours. Happy testing!

Points to be noted while testing:
•	Login:
1.	Our application has three user profiles, admin, Professor and TA. 
The credentials for admin are:
Username: admin
Password: admin
2.	Once you login as admin, you’d then be able to create accounts for Professors and TA’s. You’ll then be able to login using the same credentials.

•	Files to be tested:
1.	Our application is to be used for testing plagiarism among **python** files. 
2.	Files are picked up from git based on the homework ID provided. Be sure to have multiple repositories on your **github.ccs.neu.edu** (preferably repos like student-1, student-2 etc.). These repos should internally contain .py files. The application only considers files with .py extension.
3.	Once you reach the newTest page, you’d be asked to enter five fields namely **homework ID, github.ccs.neu username & password, the destination (path)** where you’d like your repositories to be cloned and the plagiarism strategy to be used. Be sure to provide a **valid path** – C:\Desktop\Team for instance. The files get picked up from your local repository.
4.	The homework ID entered should exist within your repo. If not, plagiarism would not be reported. 
5.	Please provide your github.ccs.neu.edu credentials as directed, select the comparison strategy and finally click on submit.
6.	Plagiarism would then be calculated and statistics would be displayed on the subsequent page.

•	Algorithms – Our application implements three algorithms namely AST, LCS and Edit Distance. 
1.	LCS: Strategy that computes the plagiarism percentage on the basis of the length of the longest common subsequence – the instructors can use this on demand for a check on common sections of code.
2.	Edit Distance: Strategy that computes the plagiarism percentage on the basis of number of edits (insert/delete/update) between two files. We’ve implemented this as a line by line approach to capture smallest of differences such as variable or function name changes.
3.	AST: An accurate strategy that computes plagiarism percentage on the basis of abstract syntax trees generated from each file.


If you run into issues at any of the above-mentioned steps, please feel free to contact us. 
Cheers,
Team 105
