# Order Management System #
| HTTP Method | URL | HTTP Status Code | Description | 
| :---: | :---: | :---: | :---: | 
| POST | api/v1/auth/signin | 200 OK | Sign in into API | 
| POST | api/v1/auth/signup | 200 OK | Sign up to API | 
| :---: | :---: | :---: | :---: | 
| GET | api/v1/order | 200 OK | Get all orders | 
| GET | api/v1/order/{id} | 200 OK | Get specific order | 
| POST | api/v1/order | 200 OK | Create order | 
| PUT | api/v1/order | 200 OK | Update order | 
| DELETE | api/v1/order/{id} | 200 OK | Delete order | 
| :---: | :---: | :---: | :---: | 
| GET | api/v1/product | 200 OK | Get all product | 
| GET | api/v1/product/{id} | 200 OK | Get specific product | 
| POST | api/v1/product | 200 OK | Create product | 
| PUT | api/v1/product | 200 OK | Update product | 
| DELETE | api/v1/product/{id} | 200 OK | Delete product | 
| :---: | :---: | :---: | :---: | 
| GET | api/v1/customer | 200 OK | Get all customer | 
| GET | api/v1/customer/{id} | 200 OK | Get specific customer | 
| POST | api/v1/customer | 200 OK | Create customer | 
| PUT | api/v1/customer | 200 OK | Update customer | 
| DELETE | api/v1/customer/{id} | 200 OK | Delete customer | 
| :---: | :---: | :---: | :---: | 
| GET | api/v1/stock | 200 OK | Get all stock | 
| GET | api/v1/stock/{id} | 200 OK | Get specific stock | 
| GET | api/v1/stock/product/{id} | 200 OK | Get a product's set of stocks | 
| POST | api/v1/stock | 200 OK | Create stock | 
| PUT | api/v1/stock | 200 OK | Update stock | 
| DELETE | api/v1/stock/{id} | 200 OK | Delete stock | 

## How to build ##
Pull image from Docker Hub
`$ docker pull scoutiano/oms-webservices:latest`

Run image
`$ docker run --name=oms-services scoutiano/oms-webservices:latest`

Image can be found at:
[Docker image](https://hub.docker.com/repository/docker/scoutiano/oms-webservices)
