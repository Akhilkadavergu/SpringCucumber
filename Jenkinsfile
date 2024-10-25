pipeline {
   
    stages {
		
		 stage ('Build') {
            steps {
                sh 'mvn clean test -Dcucumber.filter.tags="@demoTwo"' 
            }
		
	        }
	     stage ('Complete') {
            steps {                
		    echo 'job complete'
            }       }
    }
}
