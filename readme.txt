Hướng dẫn chạy
1. Cài đặt Mysql
2. Tạo database 'example'
 $ CREATE DATABASE example;
3. Tạo user 'example' với password 'example'
 $ CREATE USER 'example'@'localhost' IDENTIFIED BY 'example';
4. Cấp toàn bộ quyền của database 'example' cho user 'example'
 $ GRANT ALL PRIVILEGES ON example.* TO 'example'@'localhost';
5. Thực thi 'shopping.sql' trên database 'example' mới tạo

6. Cài đặt các thư viện bằng maven
 $ mvn clean install
7. Chạy server bằng tomcat embed
 $ mvn tomcat7:run
8. Truy cập http://localhost:9090/

