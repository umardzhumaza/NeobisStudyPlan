# How to run use Docker file

Build an image  `docker build -t restapp .`</br>
Download MySql image `docker pull mysql:latest`</br>
Set data `docker run --name localhost -e MYSQL_ROOT_PASSWORD=916052 -e MYSQL_DATABASE=clothes_store -e MYSQL_ROOT_PASSWORD=916052 -e MYSQL_PASSWORD=916052 -d mysql:latest`</br>
Verify the MySql start up logs `docker container logs localhost`</br>
Run the project `docker run -d -p 8080:8080 --name restapp --link localhost:mysql restapp`</br>
Verify the app start up logs `docker container logs restapp`</br>

# How to run use Docker-compose

1.Run docker on your computer
2.Run `docker-compose -up`