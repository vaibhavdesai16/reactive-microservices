if [ "$1" == "start" ]; then
  # Clean and build the project
  ./gradlew clean build
  # Remove the Docker image
  docker rmi consumption-report-service-spring-app:latest
  # Build a new Docker image
  docker build -t consumption-report-service-spring-app:latest .
  # Start the Docker-compose services
  docker-compose up
elif [ "$1" == "stop" ]; then
  # Stop and remove the Docker-compose services
  docker-compose down
else
  echo "Usage: $0 [start|stop]"
fi