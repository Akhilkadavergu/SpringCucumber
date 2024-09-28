pipeline {
    agent any 
    stages {
		
		 stage ('Build') {
            steps {
                sh 'mvn clean test -Dcucumber.filter.tags="@demoTwo"' 
            }
		
	        }
    }
}