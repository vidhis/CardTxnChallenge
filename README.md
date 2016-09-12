# CardTxnChallenge

The Level Money API - GetAllTransactions is consumed by this Project built using Maven. 

In order to run this Project you will need Maven installed on the System. 

For Mac OS : 1. Go to  Terminal 2. Type  brew install maven This should install Maven and you should be all set. Check if it was installed correctly using mvn -version This assumes that you have homebrew installed

For Windows : Follow instructions here https://www.mkyong.com/maven/how-to-install-maven-in-windows/

Pull the Project from Github at https://github.com/vidhis/CardTxnChallenge.git or unzip the one sent in mail

Navigate to the folder that has pom.xml through Terminal and give the following commands to run the Project :

This is to check if the build completes successfully

mvn compile

To get all the Per month Spent and Income for all the Transactions. It also shows the Average Spent and Income for all the months for which the API Response has data. The Result will be shown on the console and will be also present in file - AllSpendingIncome.txt 

mvn exec:java -Dexec.mainClass="com.vidhi.cardtest.carddetails.App" -Dexec.classpathScope=runtime

This will show all the Spending and Income per Month. It also shows Average without the Transactions that were for Donuts identifed by Merchant:DUNKIN #336784 and Krispy Kreme Donuts The Result will be shown on the console and will be also present in file - NoDonut.txt

mvn exec:java -Dexec.mainClass=com.vidhi.cardtest.carddetails.App -Dexec.classpathScope=runtime -Dexec.args="ignore-donuts"

The Files AllSpendingIncome.txt and NoDonut.txt are deleted from the previous run
At a time you will either see  AllSpendingIncome.txt or NoDonut.txt in the folder /src/main/java/com/vidhi/cardtest/carddetails/ based on the option that you choose from above. If you want to compare both you need to save the file from first run somewhere else and then run the remaining option again


The Project includes the following Java Files: 

App.java - Driver which reads API Response stored earlier into Transactions.txt and writes all calculated Transactions to AllSpendingIncome.txt Income and Spent for Transactions that dont include Donuts are written to NoDonut.txt
All 3 files can be found in your <currentDir>/src/main/java/com/vidhi/cardtest/carddetails/ folder 
LoadJson.java - Class that loads API response into a local file - Transactions.txt It uses the uid, authentication token and API token provided for this challenge. Uses HTTPClient to interact with the API
TxnHistory.java - Has the Template for the API Response in JSON. Class contains a String which tells if there is an error or not and an array of Transaction details coming from the Response.
ParseTxnJson.java - Class that does the actual calculation. It reads data from the Transaction array, parses them to find the ones that fall into same month. 
Method - CalcValNoArrayList - Calculates Income, Expenses per Month. Also calculates Average Income and Expenses for all the months. 
Average spent = (Total Spent for all Transactions)/No. Of Months for which there are transactions in the API Response
Average Income = (Total Income for all Transactions)/No. Of Months for which there are transactions in the API Response
Method - CalcValNoDonut - Excludes the Transactions by Merchant: DUNKIN #336784 and Krispy Kreme Donuts
Calculates Income, Expenses for these no Donut Transactions per Month. Also calculates Average Income and Expenses for all the months. 
Average spent = (Total Spent for no Donut Transactions)/No. Of Months for which there are transactions in the API Response
Average Income = (Total Income for no Donut Transactions)/No. Of Months for which there are transactions in the API Response
OutputObj.java - Class which has a template for the object that is outputted to file. Has YYYY-MM, Spent and Earned Details for the user in API-Response. Has overloaded toString method to match expected Output format. 
Transaction.java - Class which defines how each Transaction from API response would be. Has  Getter and Setter methods for the variables in the class. 