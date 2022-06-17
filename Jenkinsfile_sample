pipeline{
    
    agent any
    
    stages{
        
        stage("Build"){
            steps{
                echo("Build project")
            }
        }
        
        stage("Run UTs"){
            steps{
                echo("run unit test cases")
            }
        }
        
        stage("Run SITs"){
            steps{
                echo("run integration test cases")
            }
        }
        
         stage("deploy dev"){
            steps{
                echo("deploy to dev")
            }
        }
        
        stage("deploy qa"){
            steps{
                echo("deploy to qa")
            }
        }
        
        stage("Run tests on QA"){
            steps{
                echo("Run test sanity automation on QA")
            }
        }
        
        stage("deploy stage"){
            steps{
                echo("deploy to stage")
            }
        }
        
        stage("Run tests on Stage"){
            steps{
                echo("Run test sanity automation on Stage")
            }
        }
        
        stage("deploy prod"){
            steps{
                echo("deploy to prod")
            }
        }
        
        
        
    }
    
    
    
    
}