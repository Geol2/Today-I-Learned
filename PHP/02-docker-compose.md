
## phpdocker.io

```yml
###############################################################################
#                          Generated on phpdocker.io                          #
###############################################################################
version: '3.1'
services:
    mariadb:
        image: 'mariadb:10.6'
        working_dir: /application
        volumes:
            - '.:/application'
        environment:
            - MYSQL_ROOT_PASSWORD=test
            - MYSQL_DATABASE=test
            - MYSQL_USER=1234
            - MYSQL_PASSWORD=1234
        ports:
            - '8083:3306'

    webserver:
        image: 'nginx:alpine'
        working_dir: /application
        volumes:
            - '.:/application'
            - './phpdocker/nginx/nginx.conf:/etc/nginx/conf.d/default.conf'
        ports:
            - '8080:80'

    php-fpm:
        build: phpdocker/php-fpm
        working_dir: /application
        volumes:
            - '.:/application'
            - './phpdocker/php-fpm/php-ini-overrides.ini:/etc/php/8.0/fpm/conf.d/99-overrides.ini'

```