
pipeline
{

    agent
    {
        docker { image 'maven:3.6.0-jdk-11' }
    }

    stages
    {

        stage( "Unit Tests with Software Profiling" )
        {
            steps
            {
                withSonarQubeEnv( 'sonarqube-service' )
                {
                    sh 'mvn clean package sonar:sonar'
                }
            }
        }

        stage( "Wait 4 Quality Judgement" )
        {
            steps
            {
                timeout( time: 2, unit: 'MINUTES' )
                {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage( "Push to Docker Registry" )
        {
            steps
            {
                script
                {
                    docker.withRegistry('http://172.27.78.33:5000')
                    {
                        def customImage = docker.build( "${env.JOB_NAME}:1.0.${env.BUILD_ID}-${env.GIT_COMMIT.take(7)}" )
                        customImage.push()
                        customImage.push( 'latest' )
                    }
                }
            }

        }

    }

}
