docker run --name bublebee-mysql -e MYSQL_ROOT_PASSWORD=root_123 -e MYSQL_USER=bumblebee_admin -e MYSQL_PASSWORD=bumblebee_admin123 -e MYSQL_DATABASE=bumblebee_store -v bublebee_mysql_volume:/var/lib/mysql -p 3308:3306 -d mysql:8.0