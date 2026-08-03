# Complete Code Extraction from the Book

Based on the book content, I'll extract and organize all the code examples chapter by chapter. Here's the complete code structure:

## Project Root Structure

```
Java-Microservices-Containers-Cloud/
├── ch01/
│   └── ch01-01/
│       ├── 02-ProductWeb/
│       ├── pom.xml
│       ├── make.sh
│       ├── run.sh
│       └── makeandrun.sh
├── ch02/
│   ├── ch02-01/
│   ├── ch02-02/
│   └── ch02-03/
├── ch03/
│   ├── ch03-01/
│   ├── ch03-02/
│   └── ch03-03/
├── ch04/
│   ├── ch04-01/
│   └── ch04-02/
├── ch05/
│   ├── ch05-01/
│   ├── ch05-02/
│   └── kafka-request-reply-util/
├── ch06/
│   ├── ch06-01/
│   ├── ch06-02/
│   └── ch06-03/
├── ch07/
│   ├── ch07-01/
│   ├── ch07-02/
│   └── ch07-03/
├── ch08/
│   ├── ch08-01/
│   ├── ch08-02/
│   ├── ch08-03/
│   ├── ch08-04/
│   └── ch08-05/
├── ch09/
│   ├── ch09-01/
│   ├── ch09-02/
│   ├── ch09-03/
│   └── ch09-04/
├── ch10/
│   ├── ch10-01/
│   └── ch10-02/
├── ch11/
│   ├── ch11-01/
│   ├── ch11-02/
│   └── ch11-03/
├── ch12/
│   ├── ch12-01/
│   ├── ch12-02/
│   ├── ch12-03/
│   ├── ch12-04/
│   ├── ch12-05/
│   ├── ch12-06/
│   └── ch12-07/
├── ch13/
│   └── ch13-01/
├── ch14/
│   └── ch14-01/
└── ch15/
    └── ch15-01/
```

---

## Chapter 1: Microservices for the Enterprise

### ch01/ch01-01/02-ProductWeb/pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.acme.ecom.product</groupId>
    <artifactId>Ecom-Product-Web-Microservice</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>Ecom-Product-Web-Microservice</name>
    <description>Ecom Product Web Microservice</description>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.0</version>
        <relativePath/>
    </parent>

    <properties>
        <java.version>17</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

### ch01/ch01-01/02-ProductWeb/src/main/java/com/acme/ecom/product/EcomProdMicroApp1.java
```java
package com.acme.ecom.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcomProdMicroApp1 {
    public static void main(String[] args) {
        SpringApplication.run(EcomProdMicroApp1.class, args);
    }
}
```

### ch01/ch01-01/02-ProductWeb/src/main/java/com/acme/ecom/product/InitComponent.java
```java
package com.acme.ecom.product;

import com.acme.ecom.product.db.InMemoryDB;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitComponent.class);
    
    @Autowired
    private InMemoryDB inMemoryDB;
    
    @PostConstruct
    public void init() {
        LOGGER.info("Start");
        LOGGER.debug("Doing Nothing...");
        LOGGER.info("End");
    }
}
```

### ch01/ch01-01/02-ProductWeb/src/main/java/com/acme/ecom/product/controller/ProdRestConConfig.java
```java
package com.acme.ecom.product.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ProdRestConConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}
```

### ch01/ch01-01/02-ProductWeb/src/main/java/com/acme/ecom/product/controller/ProdRestContr.java
```java
package com.acme.ecom.product.controller;

import com.acme.ecom.product.db.InMemoryDB;
import com.acme.ecom.product.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class ProdRestContr {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProdRestContr.class);
    
    @Autowired
    private InMemoryDB inMemoryDB;
    
    @RequestMapping(value = "/productsweb", 
                    method = RequestMethod.GET, 
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Product>> getAllProducts() {
        LOGGER.info("Start");
        List<Product> products = inMemoryDB.getAllProducts();
        if (products.isEmpty()) {
            return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
        }
        List<Product> list = new ArrayList<Product>();
        for (Product product : products) {
            LOGGER.debug("Product [{}]", product);
            list.add(product);
        }
        LOGGER.info("Ending...");
        return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/productsweb/{productId}", 
                    method = RequestMethod.GET, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable("productId") String productId) {
        Product product = inMemoryDB.getProduct(productId);
        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/productsweb", 
                    method = RequestMethod.POST, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product productFound = inMemoryDB.getProduct(product.getProductId());
        if (null != productFound) {
            return new ResponseEntity<Product>(HttpStatus.CONFLICT);
        }
        inMemoryDB.addProduct(product);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/productsweb/{productId}", 
                    method = RequestMethod.DELETE, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> deleteProduct(@PathVariable("productId") String productId) {
        Product productFound = inMemoryDB.getProduct(productId);
        if (productFound == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        inMemoryDB.deleteProduct(productId);
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/productsweb/{productId}", 
                    method = RequestMethod.PUT, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") String productId,
                                                  @RequestBody Product product) {
        Product currentProduct = inMemoryDB.getProduct(productId);
        if (currentProduct == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        currentProduct.setName(product.getName());
        currentProduct.setCode(product.getCode());
        currentProduct.setTitle(product.getTitle());
        currentProduct.setPrice(product.getPrice());
        Product newProduct = inMemoryDB.updateProduct(currentProduct);
        return new ResponseEntity<Product>(newProduct, HttpStatus.OK);
    }
}
```

### ch01/ch01-01/02-ProductWeb/src/main/java/com/acme/ecom/product/db/InMemoryDB.java
```java
package com.acme.ecom.product.db;

import com.acme.ecom.product.model.Product;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryDB {
    private static final Logger LOGGER = LoggerFactory.getLogger(InMemoryDB.class);
    private final Map<String, Product> productStore = new HashMap<>();
    
    @PostConstruct
    private void initDB() {
        LOGGER.info("Start");
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("1", "Kamsung D3", "KAMSUNG-TRIOS", 
                "Kamsung Trios 12 inch, black, 12 px ....", 12000.0));
        products.add(new Product("2", "Lokia Pomia", "LOKIA-POMIA", 
                "Lokia 12 inch, white, 14px ....", 9000.0));
        products.forEach(product -> productStore.put(product.getProductId(), product));
        LOGGER.info("Ending.");
    }
    
    public Product getProduct(String productId) {
        return productStore.get(productId);
    }
    
    public List<Product> getAllProducts() {
        return new ArrayList<Product>(productStore.values());
    }
    
    public void addProduct(Product product) {
        productStore.put(product.getProductId(), product);
    }
    
    public void deleteProduct(String productId) {
        productStore.remove(productId);
    }
    
    public Product updateProduct(Product product) {
        productStore.put(product.getProductId(), product);
        return product;
    }
}
```

### ch01/ch01-01/02-ProductWeb/src/main/java/com/acme/ecom/product/model/Product.java
```java
package com.acme.ecom.product.model;

public class Product {
    private String productId;
    private String name;
    private String code;
    private String title;
    private Double price;
    
    public Product() {}
    
    public Product(String productId, String name, String code, String title, Double price) {
        this.productId = productId;
        this.name = name;
        this.code = code;
        this.title = title;
        this.price = price;
    }
    
    // Getters and Setters
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    
    @Override
    public String toString() {
        return "Product [productId=" + productId + ", name=" + name + ", code=" + code + 
               ", title=" + title + ", price=" + price + "]";
    }
}
```

### ch01/ch01-01/02-ProductWeb/src/main/java/com/acme/ecom/product/model/ProductCategory.java
```java
package com.acme.ecom.product.model;

public class ProductCategory {
    private String id;
    private String name;
    private String title;
    private String description;
    private String imgUrl;
    
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getImgUrl() { return imgUrl; }
    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }
}
```

### ch01/ch01-01/02-ProductWeb/src/main/resources/application.properties
```properties
server.port=8080
spring.application.name=product-web
```

### ch01/ch01-01/02-ProductWeb/src/main/resources/log4j2-spring.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n"/>
        </Console>
    </Appenders>
    <Loggers>
        <Logger name="com.acme" level="debug"/>
        <Root level="info">
            <AppenderRef ref="Console"/>
        </Root>
    </Loggers>
</Configuration>
```

### ch01/ch01-01/02-ProductWeb/src/main/resources/static/css/app.css
```css
body {
    color: #404E67;
    background: #F5F7FA;
    font-family: 'Open Sans', sans-serif;
}
.table-wrapper {
    width: 700px;
    margin: 30px auto;
    background: #fff;
    padding: 20px;
    box-shadow: 0 1px 1px rgba(0,0,0,.05);
}
.table-title {
    padding-bottom: 10px;
    margin: 0 0 10px;
}
.table-title h2 {
    margin: 6px 0 0;
    font-size: 22px;
}
.table-title .btn-group {
    float: right;
}
.table-title .btn {
    color: #fff;
    float: right;
    font-size: 13px;
    border: none;
    min-width: 50px;
    border-radius: 2px;
    border: none;
    outline: none !important;
    margin-left: 10px;
}
.table-title .btn i {
    float: left;
    font-size: 21px;
    margin-right: 5px;
}
.table-title .btn span {
    float: left;
    margin-top: 2px;
}
table.table tr th, table.table tr td {
    border-color: #e9e9e9;
    padding: 12px 15px;
    vertical-align: middle;
}
table.table tr th:first-child {
    width: 60px;
}
table.table tr th:last-child {
    width: 100px;
}
table.table-striped tbody tr:nth-of-type(odd) {
    background-color: #fcfcfc;
}
table.table-striped.table-hover tbody tr:hover {
    background: #f5f5f5;
}
table.table th i {
    font-size: 13px;
    margin: 0 5px;
    cursor: pointer;
}
table.table td:last-child i {
    opacity: 0.9;
    font-size: 22px;
    margin: 0 5px;
}
table.table td a {
    font-weight: bold;
    color: #566787;
    display: inline-block;
    text-decoration: none;
    outline: none !important;
}
table.table td a:hover {
    color: #2196F3;
}
table.table td a.edit {
    color: #FFC107;
}
table.table td a.delete {
    color: #F44336;
}
table.table td i {
    font-size: 19px;
}
table.table .avatar {
    border-radius: 50%;
    vertical-align: middle;
    margin-right: 10px;
}
.status {
    font-size: 30px;
    margin: 2px 2px 0 0;
    display: inline-block;
    vertical-align: middle;
    line-height: 10px;
}
.text-success {
    color: #10c469;
}
.text-info {
    color: #62c9e8;
}
.text-warning {
    color: #FFC107;
}
.text-danger {
    color: #ff5b5b;
}
.pagination {
    float: right;
    margin: 0 0 5px;
}
.pagination li a {
    border: none;
    font-size: 13px;
    min-width: 30px;
    min-height: 30px;
    color: #999;
    margin: 0 2px;
    line-height: 30px;
    border-radius: 2px !important;
    text-align: center;
    padding: 0 6px;
}
.pagination li a:hover {
    color: #666;
}
.pagination li.active a, .pagination li.active a.page-link {
    background: #03A9F4;
}
.pagination li.active a:hover {
    background: #0397d6;
}
.pagination li.disabled i {
    color: #ccc;
}
.pagination li i {
    font-size: 16px;
    padding-top: 6px
}
.hint-text {
    float: left;
    margin-top: 10px;
    font-size: 13px;
}
.modal .modal-dialog .modal-title {
    font-size: 24px;
}
```

### ch01/ch01-01/02-ProductWeb/src/main/resources/static/product.html
```html
<!DOCTYPE html>
<html ng-app="myApp">
<head>
    <title>Bootstrap CRUD Data Table for Database with Modal Form</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <link rel="stylesheet" href="css/app.css">
</head>
<body ng-controller="prodController as vm">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2>Manage <b>Products</b></h2>
                </div>
                <div class="col-sm-6">
                    <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal">
                        <i class="material-icons">&#xE147;</i> 
                        <span>Add New Product</span>
                    </a>
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Code</th>
                    <th>Title</th>
                    <th>Price</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <tr ng-repeat="p in vm.products">
                    <td><span ng-bind="p.productId"></span></td>
                    <td><span ng-bind="p.name"></span></td>
                    <td><span ng-bind="p.code"></span></td>
                    <td><span ng-bind="p.title"></span></td>
                    <td><span ng-bind="p.price"></span></td>
                    <td>
                        <a href="#editEmployeeModal" class="edit" data-toggle="modal"
                           ng-click="vm.populateForm(p)">
                            <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>
                        </a>
                        <a href="#deleteEmployeeModal" class="delete" data-toggle="modal"
                           ng-click="vm.deleteProduct(p)">
                            <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>
                        </a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    
    <!-- Add Modal HTML -->
    <div id="addEmployeeModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form ng-submit="vm.addProduct()">
                    <div class="modal-header">
                        <h4 class="modal-title">Product Details</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Product Id</label>
                            <input type="text" class="form-control" ng-model="vm.newProduct.productId" required>
                        </div>
                        <div class="form-group">
                            <label>Name</label>
                            <input type="text" class="form-control" ng-model="vm.newProduct.name" required>
                        </div>
                        <div class="form-group">
                            <label>Code</label>
                            <input type="text" class="form-control" ng-model="vm.newProduct.code" required>
                        </div>
                        <div class="form-group">
                            <label>Title</label>
                            <textarea class="form-control" ng-model="vm.newProduct.title" required></textarea>
                        </div>
                        <div class="form-group">
                            <label>Price</label>
                            <input type="number" class="form-control" ng-model="vm.newProduct.price" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" class="btn btn-success" value="Add">
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <!-- Edit Modal HTML -->
    <div id="editEmployeeModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form ng-submit="vm.updateProduct()">
                    <div class="modal-header">
                        <h4 class="modal-title">Product Details</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Product Id</label>
                            <input type="text" class="form-control" ng-model="vm.product.productId" required disabled>
                        </div>
                        <div class="form-group">
                            <label>Name</label>
                            <input type="text" class="form-control" ng-model="vm.product.name" required>
                        </div>
                        <div class="form-group">
                            <label>Code</label>
                            <input type="text" class="form-control" ng-model="vm.product.code" required>
                        </div>
                        <div class="form-group">
                            <label>Title</label>
                            <textarea class="form-control" ng-model="vm.product.title" required></textarea>
                        </div>
                        <div class="form-group">
                            <label>Price</label>
                            <input type="number" class="form-control" ng-model="vm.product.price" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" class="btn btn-info" value="Update">
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <!-- Delete Modal HTML -->
    <div id="deleteEmployeeModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form ng-submit="vm.confirmDelete()">
                    <div class="modal-header">
                        <h4 class="modal-title">Delete Product</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">
                        <p>Are you sure you want to delete these Records?</p>
                        <p class="text-warning"><small>This action cannot be undone.</small></p>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" class="btn btn-danger" value="Delete">
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <script>
        var app = angular.module('myApp', []);
        app.controller('prodController', ['$http', function($http) {
            var vm = this;
            vm.products = [];
            vm.newProduct = {};
            vm.product = {};
            vm.productToDelete = {};
            
            vm.getAllProducts = function() {
                $http.get('/productsweb')
                    .then(function(response) {
                        vm.products = response.data;
                    })
                    .catch(function(error) {
                        console.log('Error: ' + error);
                    });
            };
            
            vm.addProduct = function() {
                $http.post('/productsweb', vm.newProduct)
                    .then(function(response) {
                        vm.getAllProducts();
                        $('#addEmployeeModal').modal('hide');
                        vm.newProduct = {};
                    })
                    .catch(function(error) {
                        console.log('Error: ' + error);
                    });
            };
            
            vm.populateForm = function(product) {
                vm.product = angular.copy(product);
            };
            
            vm.updateProduct = function() {
                $http.put('/productsweb/' + vm.product.productId, vm.product)
                    .then(function(response) {
                        vm.getAllProducts();
                        $('#editEmployeeModal').modal('hide');
                    })
                    .catch(function(error) {
                        console.log('Error: ' + error);
                    });
            };
            
            vm.deleteProduct = function(product) {
                vm.productToDelete = product;
            };
            
            vm.confirmDelete = function() {
                $http.delete('/productsweb/' + vm.productToDelete.productId)
                    .then(function(response) {
                        vm.getAllProducts();
                        $('#deleteEmployeeModal').modal('hide');
                    })
                    .catch(function(error) {
                        console.log('Error: ' + error);
                    });
            };
            
            vm.getAllProducts();
        }]);
    </script>
</body>
</html>
```

### ch01/ch01-01/make.sh
```bash
#!/bin/bash
mvn -Dmaven.test.skip=true clean package
```

### ch01/ch01-01/run.sh
```bash
#!/bin/bash
java -jar -Dserver.port=8080 ./02-ProductWeb/target/Ecom-Product-Web-Microservice-0.0.1-SNAPSHOT.jar
```

### ch01/ch01-01/makeandrun.sh
```bash
#!/bin/bash
mvn -Dmaven.test.skip=true clean package
java -jar -Dserver.port=8080 ./02-ProductWeb/target/Ecom-Product-Web-Microservice-0.0.1-SNAPSHOT.jar
```

---

## Chapter 2: More Hands-on Microservices

### ch02/ch02-01/01-ProductServer/pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.acme.ecom.product</groupId>
    <artifactId>Ecom-Product-Server-Microservice</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.0</version>
        <relativePath/>
    </parent>

    <properties>
        <java.version>17</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-mongodb</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

### ch02/ch02-01/01-ProductServer/src/main/java/com/acme/ecom/product/EcomProductMicroApp.java
```java
package com.acme.ecom.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.acme.ecom.product.repository")
public class EcomProductMicroApp {
    public static void main(String[] args) {
        SpringApplication.run(EcomProductMicroApp.class, args);
    }
}
```

### ch02/ch02-01/01-ProductServer/src/main/java/com/acme/ecom/product/InitComponent.java
```java
package com.acme.ecom.product;

import com.acme.ecom.product.model.Product;
import com.acme.ecom.product.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitComponent.class);
    
    @Autowired
    private ProductRepository productRepository;
    
    @PostConstruct
    public void init() {
        LOGGER.info("Start");
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("1", "Kamsung D3", "KAMSUNG-TRIOS", 
                "Kamsung Trios 12 inch, black, 12 px ....", 12000.0));
        products.add(new Product("2", "Lokia Pomia", "LOKIA-POMIA", 
                "Lokia 12 inch, white, 14px ....", 9000.0));
        
        LOGGER.debug("Deleting all existing data on start...");
        productRepository.deleteAll();
        
        LOGGER.debug("Creating initial data on start...");
        productRepository.saveAll(products);
        LOGGER.info("End");
    }
}
```

### ch02/ch02-01/01-ProductServer/src/main/java/com/acme/ecom/product/controller/ProdRestControl.java
```java
package com.acme.ecom.product.controller;

import com.acme.ecom.product.model.Product;
import com.acme.ecom.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class ProdRestControl {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProdRestControl.class);
    
    @Autowired
    private ProductRepository productRepository;
    
    @RequestMapping(value = "/products", 
                    method = RequestMethod.GET, 
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
        }
        List<Product> list = new ArrayList<Product>();
        for (Product product : products) {
            LOGGER.debug("Product [{}]", product);
            list.add(product);
        }
        return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/products/{productId}", 
                    method = RequestMethod.GET, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable("productId") String productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (!product.isPresent()) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/products", 
                    method = RequestMethod.POST, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Optional<Product> productFound = productRepository.findById(product.getProductId());
        if (productFound.isPresent()) {
            return new ResponseEntity<Product>(HttpStatus.CONFLICT);
        }
        productRepository.save(product);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/products/{productId}", 
                    method = RequestMethod.DELETE, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> deleteProduct(@PathVariable("productId") String productId) {
        Optional<Product> productFound = productRepository.findById(productId);
        if (!productFound.isPresent()) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        productRepository.deleteById(productId);
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/products/{productId}", 
                    method = RequestMethod.PUT, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") String productId,
                                                  @RequestBody Product product) {
        Optional<Product> currentProductOpt = productRepository.findById(productId);
        if (!currentProductOpt.isPresent()) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        Product currentProduct = currentProductOpt.get();
        currentProduct.setName(product.getName());
        currentProduct.setCode(product.getCode());
        currentProduct.setTitle(product.getTitle());
        currentProduct.setPrice(product.getPrice());
        Product newProduct = productRepository.save(currentProduct);
        return new ResponseEntity<Product>(newProduct, HttpStatus.OK);
    }
}
```

### ch02/ch02-01/01-ProductServer/src/main/java/com/acme/ecom/product/model/Product.java
```java
package com.acme.ecom.product.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
public class Product {
    @Id
    private String productId;
    private String name;
    private String code;
    private String title;
    private Double price;
    
    public Product() {}
    
    public Product(String productId, String name, String code, String title, Double price) {
        this.productId = productId;
        this.name = name;
        this.code = code;
        this.title = title;
        this.price = price;
    }
    
    // Getters and Setters
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    
    @Override
    public String toString() {
        return "Product [productId=" + productId + ", name=" + name + ", code=" + code + 
               ", title=" + title + ", price=" + price + "]";
    }
}
```

### ch02/ch02-01/01-ProductServer/src/main/java/com/acme/ecom/product/repository/ProductRepository.java
```java
package com.acme.ecom.product.repository;

import com.acme.ecom.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "productdata", path = "productdata")
public interface ProductRepository extends MongoRepository<Product, String> {
    public List<Product> findByCode(@Param("code") String code);
}
```

### ch02/ch02-01/01-ProductServer/src/main/resources/application.properties
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/test
server.port=8081
spring.application.name=product-server
```

### ch02/ch02-01/02-ProductWeb/pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.acme.ecom.product</groupId>
    <artifactId>Ecom-Product-Web-Microservice</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.0</version>
        <relativePath/>
    </parent>

    <properties>
        <java.version>17</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

### ch02/ch02-01/02-ProductWeb/src/main/java/com/acme/ecom/product/EcomProductMicroApp.java
```java
package com.acme.ecom.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcomProductMicroApp {
    public static void main(String[] args) {
        SpringApplication.run(EcomProductMicroApp.class, args);
    }
}
```

### ch02/ch02-01/02-ProductWeb/src/main/java/com/acme/ecom/product/InitComponent.java
```java
package com.acme.ecom.product;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class InitComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitComponent.class);
    
    @PostConstruct
    public void init() {
        LOGGER.info("Start");
        LOGGER.debug("Doing Nothing...");
        LOGGER.info("End");
    }
}
```

### ch02/ch02-01/02-ProductWeb/src/main/java/com/acme/ecom/product/controller/ProdRestConConfig.java
```java
package com.acme.ecom.product.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class ProdRestConConfig {
    @Bean
    RestTemplate restTemplate() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/json"));
        converter.setObjectMapper(mapper);
        return new RestTemplate(Arrays.asList(converter));
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}
```

### ch02/ch02-01/02-ProductWeb/src/main/java/com/acme/ecom/product/controller/ProductRestController.java
```java
package com.acme.ecom.product.controller;

import com.acme.ecom.product.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@CrossOrigin
public class ProductRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);
    
    @Value("${acme.PRODUCT_SERVICE_URL}")
    private String PRODUCT_SERVICE_URL;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @RequestMapping(value = "/productsweb", 
                    method = RequestMethod.GET, 
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Product>> getAllProducts() {
        ParameterizedTypeReference<List<Product>> responseTypeRef = 
            new ParameterizedTypeReference<List<Product>>() {};
        ResponseEntity<List<Product>> entity = restTemplate.exchange(
            PRODUCT_SERVICE_URL, HttpMethod.GET, (HttpEntity<Product>) null, responseTypeRef);
        List<Product> productList = entity.getBody();
        return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/productsweb/{productId}", 
                    method = RequestMethod.GET, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable("productId") String productId) {
        String uri = PRODUCT_SERVICE_URL + "/" + productId;
        Product product = restTemplate.getForObject(uri, Product.class);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/productsweb", 
                    method = RequestMethod.POST, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product productNew = restTemplate.postForObject(PRODUCT_SERVICE_URL, product, Product.class);
        return new ResponseEntity<Product>(productNew, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/productsweb/{productId}", 
                    method = RequestMethod.DELETE, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> deleteProduct(@PathVariable("productId") String productId) {
        restTemplate.delete(PRODUCT_SERVICE_URL + "/" + productId);
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/productsweb/{productId}", 
                    method = RequestMethod.PUT, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") String productId,
                                                  @RequestBody Product product) {
        String uri = PRODUCT_SERVICE_URL + "/" + productId;
        restTemplate.put(uri, product, Product.class);
        Product updatedProduct = restTemplate.getForObject(uri, Product.class);
        return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
    }
}
```

### ch02/ch02-01/02-ProductWeb/src/main/java/com/acme/ecom/product/model/Product.java
```java
package com.acme.ecom.product.model;

public class Product {
    private String productId;
    private String name;
    private String code;
    private String title;
    private Double price;
    
    public Product() {}
    
    public Product(String productId, String name, String code, String title, Double price) {
        this.productId = productId;
        this.name = name;
        this.code = code;
        this.title = title;
        this.price = price;
    }
    
    // Getters and Setters
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    
    @Override
    public String toString() {
        return "Product [productId=" + productId + ", name=" + name + ", code=" + code + 
               ", title=" + title + ", price=" + price + "]";
    }
}
```

### ch02/ch02-01/02-ProductWeb/src/main/resources/application.properties
```properties
server.port=8080
spring.application.name=product-web
acme.PRODUCT_SERVICE_URL=http://localhost:8081/products
```

---

## Chapter 3: Onion and Hexagonal Architecture in Practice

### ch03/ch03-01/01-ProductServer/pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.acme.ecom.product</groupId>
    <artifactId>Ecom-Product-Server-Microservice</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.0</version>
        <relativePath/>
    </parent>

    <properties>
        <java.version>17</java.version>
        <org.mapstruct.version>1.5.5.Final</org.mapstruct.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.2.19</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.liquibase</groupId>
            <artifactId>liquibase-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct</artifactId>
            <version>${org.mapstruct.version}</version>
        </dependency>
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct-processor</artifactId>
            <version>${org.mapstruct.version}</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

### ch03/ch03-01/01-ProductServer/src/main/java/com/acme/ecom/product/EcomProductMicroserviceApplication.java
```java
package com.acme.ecom.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.acme.ecom.product.repository")
public class EcomProductMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcomProductMicroserviceApplication.class, args);
    }
}
```

### ch03/ch03-01/01-ProductServer/src/main/java/com/acme/ecom/product/InitComponent.java
```java
package com.acme.ecom.product;

import com.acme.ecom.product.model.ProductOR;
import com.acme.ecom.product.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitComponent.class);
    
    @Autowired
    private ProductRepository productRepository;
    
    @PostConstruct
    public void init() {
        LOGGER.info("Start...");
        
        LOGGER.debug("Deleting all existing data on start...");
        productRepository.deleteAll();
        
        LOGGER.debug("Creating initial data on start...");
        List<ProductOR> products = new ArrayList<ProductOR>();
        products.add(new ProductOR("1", "Kamsung D3", "KAMSUNG-TRIOS", 
                "Kamsung Trios 12 inch, black, 12 px ....", 12000.0, "Mobile"));
        products.add(new ProductOR("2", "Lokia Pomia", "LOKIA-POMIA", 
                "Lokia 12 inch, white, 14px ....", 9000.0, "Mobile"));
        
        productRepository.saveAll(products);
        LOGGER.info("End");
    }
}
```

### ch03/ch03-01/01-ProductServer/src/main/java/com/acme/ecom/product/controller/ProductMapper.java
```java
package com.acme.ecom.product.controller;

import com.acme.ecom.product.model.Product;
import com.acme.ecom.product.model.ProductOR;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    
    @Mapping(source = "productId", target = "productId")
    Product entityToApi(ProductOR entity);
    
    @Mapping(source = "productId", target = "productId")
    ProductOR apiToEntity(Product api);
}
```

### ch03/ch03-01/01-ProductServer/src/main/java/com/acme/ecom/product/controller/ProductRestController.java
```java
package com.acme.ecom.product.controller;

import com.acme.ecom.product.model.Product;
import com.acme.ecom.product.model.ProductOR;
import com.acme.ecom.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class ProductRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProductMapper mapper;
    
    @RequestMapping(value = "/products", 
                    method = RequestMethod.GET, 
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Product>> getAllProducts() {
        List<ProductOR> productORs = productRepository.findAll();
        if (productORs.isEmpty()) {
            return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
        }
        List<Product> list = new ArrayList<Product>();
        for (ProductOR productOR : productORs) {
            list.add(mapper.entityToApi(productOR));
        }
        return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/products/{productId}", 
                    method = RequestMethod.GET, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable("productId") String productId) {
        Optional<ProductOR> productOROpt = productRepository.findById(Long.parseLong(productId));
        if (!productOROpt.isPresent()) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(mapper.entityToApi(productOROpt.get()), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/products", 
                    method = RequestMethod.POST, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        ProductOR productOR = mapper.apiToEntity(product);
        productOR = productRepository.save(productOR);
        return new ResponseEntity<Product>(mapper.entityToApi(productOR), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/products/{productId}", 
                    method = RequestMethod.DELETE, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> deleteProduct(@PathVariable("productId") String productId) {
        Optional<ProductOR> productOROpt = productRepository.findById(Long.parseLong(productId));
        if (!productOROpt.isPresent()) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        productRepository.deleteById(Long.parseLong(productId));
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/products/{productId}", 
                    method = RequestMethod.PUT, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") String productId,
                                                  @RequestBody Product product) {
        Optional<ProductOR> currentProductOROpt = productRepository.findById(Long.parseLong(productId));
        if (!currentProductOROpt.isPresent()) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        ProductOR currentProductOR = currentProductOROpt.get();
        currentProductOR.setName(product.getName());
        currentProductOR.setCode(product.getCode());
        currentProductOR.setTitle(product.getTitle());
        currentProductOR.setPrice(product.getPrice());
        ProductOR newProductOR = productRepository.save(currentProductOR);
        return new ResponseEntity<Product>(mapper.entityToApi(newProductOR), HttpStatus.OK);
    }
}
```

### ch03/ch03-01/01-ProductServer/src/main/java/com/acme/ecom/product/model/Product.java
```java
package com.acme.ecom.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String productId;
    private String name;
    private String code;
    private String title;
    private Double price;
}
```

### ch03/ch03-01/01-ProductServer/src/main/java/com/acme/ecom/product/model/ProductOR.java
```java
package com.acme.ecom.product.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "product")
public class ProductOR {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid")
    private Long productId;
    
    @Column(name = "prodname")
    private String name;
    
    @Column(name = "code")
    private String code;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "price")
    private Double price;
    
    @Column(name = "category")
    private String category;
    
    public ProductOR(String productId, String name, String code, String title, Double price, String category) {
        this.productId = productId != null ? Long.parseLong(productId) : null;
        this.name = name;
        this.code = code;
        this.title = title;
        this.price = price;
        this.category = category;
    }
}
```

### ch03/ch03-01/01-ProductServer/src/main/java/com/acme/ecom/product/repository/ProductRepository.java
```java
package com.acme.ecom.product.repository;

import com.acme.ecom.product.model.ProductOR;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "productdata", path = "productdata")
public interface ProductRepository extends CrudRepository<ProductOR, Long> {
    public List<ProductOR> findByCode(@Param("code") String code);
}
```

### ch03/ch03-01/01-ProductServer/src/main/resources/application.properties
```properties
spring.application.name=product-server
server.port=8081
spring.datasource.url=jdbc:postgresql://${DB_SERVER:localhost:5432}/${POSTGRES_DB:productdb}
spring.datasource.username=${POSTGRES_USER:postgres}
spring.datasource.password=${POSTGRES_PASSWORD:postgre}
spring.liquibase.change-log=classpath:/db/changelog/db.changelog-master.xml
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.jpa.show-sql=true
```

### ch03/ch03-01/01-ProductServer/src/main/resources/db/changelog/db.changelog-master.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
                   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                   xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
                   http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd">
    <changeSet id="1" author="Binildas">
        <sqlFile path="01_init_product.sql" 
                 relativeToChangeLogFile="true" 
                 splitStatements="true" 
                 stripComments="true"/>
        <comment>Create table with Product info</comment>
    </changeSet>
</databaseChangeLog>
```

### ch03/ch03-01/01-ProductServer/src/main/resources/db/changelog/01_init_product.sql
```sql
CREATE TABLE IF NOT EXISTS productcategory (
    categoryid SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    title VARCHAR(60),
    description VARCHAR(255),
    imgUrl VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS product (
    productid SERIAL PRIMARY KEY,
    prodname VARCHAR(30) NOT NULL,
    code VARCHAR(30),
    title VARCHAR(60),
    price DECIMAL(10,2),
    category VARCHAR(30)
);
```

### ch03/ch03-01/02-ProductWeb/pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.acme.ecom.product</groupId>
    <artifactId>Ecom-Product-Web-Microservice</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.0</version>
        <relativePath/>
    </parent>

    <properties>
        <java.version>17</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

### ch03/ch03-01/02-ProductWeb/src/main/java/com/acme/ecom/product/EcomProductMicroserviceApplication.java
```java
package com.acme.ecom.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcomProductMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcomProductMicroserviceApplication.class, args);
    }
}
```

---

## Chapter 5: Microservices Integration in Practice

### ch05/kafka-request-reply-util/pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>se.callista.blog.synch_kafka</groupId>
    <artifactId>kafka-request-reply-util</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <spring-kafka.version>3.1.0</spring-kafka.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.kafka</groupId>
            <artifactId>spring-kafka</artifactId>
            <version>${spring-kafka.version}</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>2.0.9</version>
        </dependency>
    </dependencies>
</project>
```

### ch05/kafka-request-reply-util/src/main/java/se/callista/blog/synch_kafka/request_reply_util/CompletableFutureReplyingKafkaTemplate.java
```java
package se.callista.blog.synch_kafka.request_reply_util;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureReplyingKafkaTemplate<K, V, R> 
        extends PartitionAwareReplyingKafkaTemplate<K, V, R> 
        implements CompletableFutureReplyingKafkaOperations<K, V, R> {
    
    public CompletableFutureReplyingKafkaTemplate(
            ProducerFactory<K, V> producerFactory,
            GenericMessageListenerContainer<K, R> replyContainer) {
        super(producerFactory, replyContainer);
    }
    
    @Override
    public CompletableFuture<R> requestReply(String topic, V value) {
        return adapt(sendAndReceive(topic, value));
    }
    
    @Override
    public CompletableFuture<R> requestReply(String topic, K key, V value) {
        return adapt(sendAndReceive(topic, key, value));
    }
    
    @Override
    public CompletableFuture<R> requestReply(String topic, Integer partition, K key, V value) {
        return adapt(sendAndReceive(topic, partition, key, value));
    }
    
    private CompletableFuture<R> adapt(RequestReplyFuture<K, V, R> future) {
        CompletableFuture<R> completableFuture = new CompletableFuture<>();
        future.addCallback(
            result -> completableFuture.complete(result.value()),
            completableFuture::completeExceptionally
        );
        return completableFuture;
    }
}
```

### ch05/kafka-request-reply-util/src/main/java/se/callista/blog/synch_kafka/request_reply_util/PartitionAwareReplyingKafkaTemplate.java
```java
package se.callista.blog.synch_kafka.request_reply_util;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.GenericMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

import java.nio.ByteBuffer;

public class PartitionAwareReplyingKafkaTemplate<K, V, R> 
        extends ReplyingKafkaTemplate<K, V, R> {
    
    public PartitionAwareReplyingKafkaTemplate(
            ProducerFactory<K, V> producerFactory,
            GenericMessageListenerContainer<K, R> replyContainer) {
        super(producerFactory, replyContainer);
    }
    
    private TopicPartition getFirstAssignedReplyTopicPartition() {
        if (getAssignedReplyTopicPartitions() != null && 
            getAssignedReplyTopicPartitions().iterator().hasNext()) {
            TopicPartition replyPartition = getAssignedReplyTopicPartitions().iterator().next();
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Using partition " + replyPartition.partition());
            }
            return replyPartition;
        } else {
            throw new KafkaException("Illegal state: No reply partition is assigned to this instance");
        }
    }
    
    protected RequestReplyFuture<K, V, R> doSendAndReceive(ProducerRecord<K, V> record) {
        TopicPartition replyPartition = getFirstAssignedReplyTopicPartition();
        record.headers()
            .add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, replyPartition.topic().getBytes()))
            .add(new RecordHeader(KafkaHeaders.REPLY_PARTITION, intToBytesBigEndian(replyPartition.partition())));
        return super.sendAndReceive(record);
    }
    
    private byte[] intToBytesBigEndian(final int value) {
        return ByteBuffer.allocate(4).putInt(value).array();
    }
}
```

---

## Chapter 7: Docker Implementation

### ch07/ch07-03/Dockerfile
```dockerfile
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} ecom.jar
ENTRYPOINT ["java","-jar","/ecom.jar"]
```

### ch07/ch07-03/makeandrun.sh
```bash
#!/bin/bash
mvn -Dmaven.test.skip=true clean package
docker build --build-arg JAR_FILE=02-ProductWeb/target/*.jar -t binildas/product-web .
docker push binildas/product-web
sleep 10
docker rmi binildas/product-web
sleep 10
docker run -p 8080:8080 --name product-web binildas/product-web
```

---

## Chapter 8: Microservice Containers

### ch08/ch08-01/Dockerfile
```dockerfile
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} ecom.jar
ENTRYPOINT ["java","-jar","/ecom.jar"]
```

### ch08/ch08-01/makeandrun.sh
```bash
#!/bin/bash
mvn -Dmaven.test.skip=true clean package
docker build --build-arg JAR_FILE=02-ProductWeb/target/*.jar -t ecom/product-web .
docker build --build-arg JAR_FILE=01-ProductServer/target/*.jar -t ecom/product-server .
docker run -d -it -p 27017:27017 --name mongo mongo:3.6
docker run -d -p 8081:8081 --link mongo:mongo --name product-server ecom/product-server
docker run -d -p 8080:8080 --link product-server:product-server --name product-web ecom/product-web
```

### ch08/ch08-01/clean.sh
```bash
#!/bin/bash
mvn -Dmaven.test.skip=true clean
docker stop product-web
docker stop product-server
docker stop mongo
docker rm product-web
docker rm product-server
docker rm mongo
docker rmi -f ecom/product-web
docker rmi -f ecom/product-server
```

---

## Chapter 9: Composing Multi-Service Containers

### ch09/ch09-01/Dockerfile
```dockerfile
FROM maven:3.6.1-jdk-8-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -f pom.xml clean package

FROM openjdk:8-alpine
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]
```

### ch09/ch09-01/docker-compose.yml
```yaml
version: "3"
services:
  db:
    image: "postgres:9.6-alpine"
    container_name: postgres-docker
    volumes:
      - product-data:/var/lib/postgresql/data
    ports:
      - 5432:5432
    environment:
      - POSTGRES_DB=productdb
      - POSTGRES_USER=postgres
      - POSTGRES_PASSWORD=postgre
    networks:
      - ecom-network

  server:
    build: ./01-ProductServer
    image: ecom/product-server
    container_name: product-server
    ports:
      - "8081:8081"
    depends_on:
      - "db"
    environment:
      - DB_SERVER=postgres-docker:5432
      - POSTGRES_DB=productdb
      - POSTGRES_USER=postgres
      - POSTGRES_PASSWORD=postgre
    networks:
      - ecom-network

  web:
    build: ./02-ProductWeb
    image: ecom/product-web
    container_name: product-web
    depends_on:
      - "server"
    ports:
      - "8080:8080"
    environment:
      - acme.PRODUCT_SERVICE_URL=http://product-server:8081/products
    networks:
      - ecom-network

volumes:
  product-data:

networks:
  ecom-network:
```

### ch09/ch09-01/makeandrun.sh
```bash
#!/bin/bash
docker-compose up
```

### ch09/ch09-01/clean.sh
```bash
#!/bin/bash
docker-compose down
docker rmi -f ecom/product-web
docker rmi -f ecom/product-server
```

---

## Chapter 10: Microservices with Kubernetes

### ch10/ch10-01/product-web-deployment.yml
```yaml
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: product-web
  labels:
    app: product-web
    group: frontend
spec:
  replicas: 1
  selector:
    matchLabels:
      app: product-web
  template:
    metadata:
      labels:
        app: product-web
    spec:
      containers:
      - name: product-web
        image: ecom/product-web
        ports:
        - containerPort: 8080
        imagePullPolicy: Never
        env:
        - name: acme.PRODUCT_SERVICE_URL
          value: http://product-server:8081/products
```

### ch10/ch10-01/product-web-service.yml
```yaml
apiVersion: v1
kind: Service
metadata:
  name: product-web
spec:
  selector:
    app: product-web
  ports:
  - protocol: TCP
    port: 8080
    targetPort: 8080
  type: LoadBalancer
```

### ch10/ch10-01/product-server-deployment.yml
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: product-server
  labels:
    app: product-server
    group: backend
spec:
  replicas: 1
  selector:
    matchLabels:
      app: product-server
  template:
    metadata:
      labels:
        app: product-server
    spec:
      containers:
      - name: product-server
        image: ecom/product-server
        ports:
        - containerPort: 8081
        imagePullPolicy: Never
        env:
        - name: spring.data.mongodb.uri
          value: mongodb://mongo:27017/test
```

### ch10/ch10-01/product-server-service.yml
```yaml
apiVersion: v1
kind: Service
metadata:
  name: product-server
spec:
  selector:
    app: product-server
  ports:
  - protocol: TCP
    port: 8081
    targetPort: 8081
---
apiVersion: v1
kind: Service
metadata:
  name: product-server-nodeport
spec:
  selector:
    app: product-server
  ports:
  - nodePort: 30002
    port: 8081
    targetPort: 8081
  type: NodePort
```

### ch10/ch10-01/mongo-deployment.yml
```yaml
apiVersion: apps/v1
kind: StatefulSet
metadata:
  name: mongo-cluster
  labels:
    app: mongo
spec:
  replicas: 1
  serviceName: "mongo"
  selector:
    matchLabels:
      app: mongo
  template:
    metadata:
      labels:
        app: mongo
    spec:
      volumes:
      - name: data-db
        persistentVolumeClaim:
          claimName: mongo-data-db
      terminationGracePeriodSeconds: 10
      containers:
      - name: mongo
        image: mongo:4.2.24
        volumeMounts:
        - mountPath: /data/db
          name: data-db
          readOnly: false
        ports:
        - containerPort: 27017
```

### ch10/ch10-01/mongo-volume-claim.yml
```yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: mongo-data-db
spec:
  storageClassName: manual
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 3Gi
```

### ch10/ch10-01/mongo-volume.yml
```yaml
apiVersion: v1
kind: PersistentVolume
metadata:
  name: mongo-data-db
  labels:
    type: local
spec:
  storageClassName: manual
  capacity:
    storage: 10Gi
  accessModes:
    - ReadWriteOnce
  hostPath:
    path: "/home/docker/binil/mongodata"
```

### ch10/ch10-01/mongo-service.yml
```yaml
apiVersion: v1
kind: Service
metadata:
  name: mongo
spec:
  selector:
    app: mongo
  ports:
  - protocol: TCP
    port: 27017
    targetPort: 27017
---
apiVersion: v1
kind: Service
metadata:
  name: mongo-nodeport
spec:
  selector:
    app: mongo
  ports:
  - nodePort: 30001
    port: 27017
    targetPort: 27017
  type: NodePort
```

### ch10/ch10-01/makeandrun.sh
```bash
#!/bin/bash
mvn -Dmaven.test.skip=true clean package
docker build --build-arg JAR_FILE=02-ProductWeb/target/*.jar -t ecom/product-web .
docker build --build-arg JAR_FILE=01-ProductServer/target/*.jar -t ecom/product-server .

kubectl create -f mongo-volume.yml
kubectl create -f mongo-volume-claim.yml
kubectl create -f mongo-deployment.yml
kubectl create -f mongo-service.yml
kubectl create -f product-server-deployment.yml
kubectl create -f product-server-service.yml
kubectl create -f product-web-deployment.yml
kubectl create -f product-web-service.yml

minikube service product-web --url
sleep 3
kubectl get pods
kubectl get services
```

### ch10/ch10-01/clean.sh
```bash
#!/bin/bash
mvn -Dmaven.test.skip=true clean
kubectl delete -f product-web-service.yml
kubectl delete -f product-web-deployment.yml
kubectl delete -f product-server-service.yml
kubectl delete -f product-server-deployment.yml
kubectl delete -f mongo-service.yml
kubectl delete -f mongo-deployment.yml
kubectl delete -f mongo-volume-claim.yml
kubectl delete -f mongo-volume.yml
docker rmi -f ecom/product-web
docker rmi -f ecom/product-server
```

---

## Chapter 11: Message Oriented Microservices in Kubernetes

### ch11/ch11-01/kafka-deployment.yml
```yaml
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: zookeeper-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      component: zookeeper
  template:
    metadata:
      labels:
        component: zookeeper
    spec:
      containers:
      - name: zookeeper
        image: digitalwonderland/zookeeper
        ports:
        - containerPort: 2181
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: kafka-1-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      component: kafka-1
  template:
    metadata:
      labels:
        component: kafka-1
    spec:
      containers:
      - name: kafka-1
        image: pharosproduction/kafka_k8s:v1
        resources:
          requests:
            memory: "256Mi"
            cpu: "250m"
          limits:
            memory: "512Mi"
            cpu: "500m"
        ports:
        - containerPort: 9092
        env:
        - name: MY_POD_IP
          valueFrom:
            fieldRef:
              fieldPath: status.podIP
        - name: KAFKA_ADVERTISED_PORT
          value: "9092"
        - name: KAFKA_ZOOKEEPER_CONNECT
          value: zookeeper-ip-service:2181
        - name: KAFKA_ADVERTISED_HOST_NAME
          value: $(MY_POD_IP)
        - name: KAFKA_BROKER_ID
          value: "1"
```

### ch11/ch11-01/kafka-svc.yml
```yaml
---
apiVersion: v1
kind: Service
metadata:
  name: zookeeper-ip-service
spec:
  type: ClusterIP
  selector:
    component: zookeeper
  ports:
  - name: zookeeper
    port: 2181
    targetPort: 2181
---
apiVersion: v1
kind: Service
metadata:
  name: kafka-1-ip-service
spec:
  type: ClusterIP
  selector:
    component: kafka-1
  ports:
  - name: kafka
    port: 9092
    targetPort: 9092
```

---

## Chapter 12: Automating Kubernetes Deployment and Helm

### ch12/ch12-01/src/main/java/com/acme/ecom/product/Application.java
```java
package com.acme.ecom.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    private static volatile long times = 0L;
    
    @RequestMapping("/")
    public String home() {
        LOGGER.info("Start");
        ++times;
        LOGGER.debug("Inside hello.Application.home() : {}", times);
        LOGGER.info("Returning...");
        return "Hello Docker World : " + times;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        LOGGER.info("Started...");
    }
}
```

### ch12/ch12-02/pom.xml (Spotify Maven Plugin)
```xml
<build>
    <plugins>
        <plugin>
            <groupId>com.spotify</groupId>
            <artifactId>dockerfile-maven-plugin</artifactId>
            <version>1.4.13</version>
            <configuration>
                <repository>${docker.image.prefix}/${project.artifactId}</repository>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### ch12/ch12-04/k8s/deployment.yml
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: springboothelm
spec:
  replicas: 1
  selector:
    matchLabels:
      app: springboothelm
  template:
    metadata:
      labels:
        app: springboothelm
    spec:
      containers:
      - name: springboothelm
        image: binildas/spring-boot-docker-k8s-helm
        ports:
        - containerPort: 8080
```

### ch12/ch12-04/k8s/service.yml
```yaml
apiVersion: v1
kind: Service
metadata:
  name: springboothelm
spec:
  type: NodePort
  ports:
  - port: 8080
    targetPort: 8080
    nodePort: 30048
  selector:
    app: springboothelm
```

### ch12/ch12-04/run.sh
```bash
#!/bin/bash
eval $(minikube docker-env)
kubectl create -f ./k8s/deployment.yml
kubectl create -f ./k8s/service.yml
minikube service springboothelm --url
sleep 3
kubectl get pods
kubectl get services
```

### ch12/ch12-05/springboothelm/Chart.yaml
```yaml
apiVersion: v2
name: springboothelm
description: A Helm chart for Kubernetes
type: application
version: 0.1.0
appVersion: "1.16.0"
```

### ch12/ch12-05/springboothelm/values.yaml
```yaml
replicaCount: 1
image:
  repository: binildas/spring-boot-docker-k8s-helm
  pullPolicy: IfNotPresent
```

### ch12/ch12-05/springboothelm/templates/deployment.yaml
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: {{ include "springboothelm.fullname" . }}
  labels:
    {{- include "springboothelm.labels" . | nindent 4 }}
spec:
  replicas: {{ .Values.replicaCount }}
  selector:
    matchLabels:
      {{- include "springboothelm.selectorLabels" . | nindent 6 }}
  template:
    metadata:
      labels:
        {{- include "springboothelm.selectorLabels" . | nindent 8 }}
    spec:
      containers:
      - name: {{ .Chart.Name }}
        image: "{{ .Values.image.repository }}:{{ .Values.image.tag | default .Chart.AppVersion }}"
        imagePullPolicy: {{ .Values.image.pullPolicy }}
        ports:
        - containerPort: 8080
```

### ch12/ch12-06/helmfile.yaml
```yaml
repositories:
  - name: stable
    url: https://charts.helm.sh/stable

releases:
  - name: postgres
    chart: ./charts/postgres
    values:
      - ./values/acme-postgres.yaml

  - name: adminer
    chart: ./charts/app
    values:
      - ./values/adminer.yaml

  - name: product-server
    chart: ./charts/app
    values:
      - ./values/acme-product-server.yaml

  - name: product-web
    chart: ./charts/app
    values:
      - ./values/acme-product-web.yaml

  - name: ingress-backend
    chart: stable/nginx-ingress
    version: 1.36.0

  - name: ingress-controller
    chart: ./charts/ingress
    values:
      - ./values/ingress.yaml
```

### ch12/ch12-06/run.sh
```bash
#!/bin/bash
mvn -Dmaven.test.skip=true clean package
eval $(minikube docker-env)
docker build --build-arg JAR_FILE=02-ProductWeb/target/*.jar -t ecom/product-web .
docker build --build-arg JAR_FILE=01-ProductServer/target/*.jar -t ecom/product-server .

helm install -f acme-postgres.yaml postgres ./postgres
helm install -f adminer.yaml adminer ./app
helm install -f acme-product-server.yaml product-server ./app
helm install -f acme-product-web.yaml product-web ./app
helm install -f ingress.yaml ingress ./ingress

minikube service product-web --url
sleep 3
helm list
kubectl get deployments
```

---

## Chapter 13: CI/CD for Microservice Containers

### ch13/ch13-01/skaffold.yaml
```yaml
apiVersion: skaffold/v1beta4
kind: Config
build:
  local:
    push: false
  artifacts:
  - image: binildas/spring-boot-docker-k8s-helm
    jibMaven: {}
deploy:
  kubectl:
    manifests:
    - k8s/*.yml
```

### ch13/ch13-01/k8s/deployment.yml
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: springboothelm
spec:
  replicas: 1
  selector:
    matchLabels:
      app: springboothelm
  template:
    metadata:
      labels:
        app: springboothelm
    spec:
      containers:
      - name: springboothelm
        image: binildas/spring-boot-docker-k8s-helm
        ports:
        - containerPort: 8080
```

### ch13/ch13-01/k8s/service.yml
```yaml
apiVersion: v1
kind: Service
metadata:
  name: springboothelm
spec:
  type: NodePort
  ports:
  - port: 8080
    targetPort: 8080
  selector:
    app: springboothelm
```

---

## Chapter 14: Microservices in AWS Elastic Compute Cloud

### ch14/ch14-01/connections.tf
```hcl
provider "aws" {
  region = "ap-southeast-1"
}
```

### ch14/ch14-01/network.tf
```hcl
resource "aws_vpc" "test-env" {
  cidr_block           = "10.0.0.0/16"
  enable_dns_hostnames = true
  enable_dns_support   = true
  tags = {
    Name = "bdca-Instance-03"
  }
}

resource "aws_eip" "ip-test-env" {
  instance = "${aws_instance.test-ec2-instance.id}"
  domain   = "vpc"
}
```

### ch14/ch14-01/subnets.tf
```hcl
resource "aws_subnet" "subnet-uno" {
  cidr_block        = "${cidrsubnet(aws_vpc.test-env.cidr_block, 3, 1)}"
  vpc_id            = "${aws_vpc.test-env.id}"
  availability_zone = "ap-southeast-1a"
}

resource "aws_route_table" "route-table-test-env" {
  vpc_id = "${aws_vpc.test-env.id}"
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = "${aws_internet_gateway.test-env-gw.id}"
  }
  tags = {
    Name = "test-env-route-table"
  }
}

resource "aws_route_table_association" "subnet-association" {
  subnet_id      = "${aws_subnet.subnet-uno.id}"
  route_table_id = "${aws_route_table.route-table-test-env.id}"
}
```

### ch14/ch14-01/gateways.tf
```hcl
resource "aws_internet_gateway" "test-env-gw" {
  vpc_id = "${aws_vpc.test-env.id}"
  tags = {
    Name = "test-env-gw"
  }
}
```

### ch14/ch14-01/security.tf
```hcl
resource "aws_security_group" "ingress-all-test" {
  name   = "allow-all-sg"
  vpc_id = "${aws_vpc.test-env.id}"
  
  ingress {
    cidr_blocks = ["0.0.0.0/0"]
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
  }
  
  ingress {
    cidr_blocks = ["0.0.0.0/0"]
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
  }
  
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}
```

### ch14/ch14-01/servers.tf
```hcl
resource "aws_instance" "test-ec2-instance" {
  ami             = "${var.ami_id}"
  instance_type   = "t2.micro"
  key_name        = "${var.ami_key_pair_name}"
  security_groups = ["${aws_security_group.ingress-all-test.id}"]
  tags = {
    Name = "${var.ami_name}"
  }
  subnet_id = "${aws_subnet.subnet-uno.id}"
}
```

### ch14/ch14-01/variables.tf
```hcl
variable "ami_name" {}
variable "ami_id" {}
variable "ami_key_pair_name" {}
```

---

## Chapter 15: Microservices in AWS Elastic Kubernetes Service

### ch15/ch15-01/providers.tf
```hcl
terraform {
  required_version = ">= 0.12"
}

provider "aws" {
  region = var.aws_region
}

data "aws_availability_zones" "available" {}

provider "http" {}
```

### ch15/ch15-01/variables.tf
```hcl
variable "aws_region" {
  default = "ap-southeast-1"
}

variable "cluster-name" {
  default = "bdca-tf-eks-01"
  type    = string
}
```

### ch15/ch15-01/workstation-external-ip.tf
```hcl
data "http" "workstation-external-ip" {
  url = "http://ipv4.icanhazip.com"
}

locals {
  workstation-external-cidr = "${chomp(data.http.workstation-external-ip.body)}/32"
}
```

### ch15/ch15-01/vpc.tf
```hcl
resource "aws_vpc" "demo" {
  cidr_block = "10.0.0.0/16"
  tags = tomap(
    {"Name" = "terraform-eks-demo-node",
     "kubernetes.io/cluster/${var.cluster-name}" = "shared"}
  )
}

resource "aws_subnet" "demo" {
  count = 2
  availability_zone = data.aws_availability_zones.available.names[count.index]
  cidr_block = "10.0.${count.index}.0/24"
  map_public_ip_on_launch = true
  vpc_id = aws_vpc.demo.id
  tags = tomap(
    {"Name" = "terraform-eks-demo-node",
     "kubernetes.io/cluster/${var.cluster-name}" = "shared"}
  )
}

resource "aws_internet_gateway" "demo" {
  vpc_id = aws_vpc.demo.id
  tags = {
    Name = "terraform-eks-demo"
  }
}

resource "aws_route_table" "demo" {
  vpc_id = aws_vpc.demo.id
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.demo.id
  }
}

resource "aws_route_table_association" "demo" {
  count = 2
  subnet_id = aws_subnet.demo.*.id[count.index]
  route_table_id = aws_route_table.demo.id
}
```

### ch15/ch15-01/eks-cluster.tf
```hcl
resource "aws_iam_role" "demo-cluster" {
  name = "terraform-eks-demo-cluster"
  assume_role_policy = <<POLICY
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "Service": "eks.amazonaws.com"
      },
      "Action": "sts:AssumeRole"
    }
  ]
}
POLICY
}

resource "aws_iam_role_policy_attachment" "demo-cluster-AmazonEKSClusterPolicy" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKSClusterPolicy"
  role = aws_iam_role.demo-cluster.name
}

resource "aws_iam_role_policy_attachment" "demo-cluster-AmazonEKSVPCResourceController" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKSVPCResourceController"
  role = aws_iam_role.demo-cluster.name
}

resource "aws_security_group" "demo-cluster" {
  name = "terraform-eks-demo-cluster"
  description = "Cluster communication with worker nodes"
  vpc_id = aws_vpc.demo.id
  egress {
    from_port = 0
    to_port = 0
    protocol = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
  tags = {
    Name = "terraform-eks-demo"
  }
}

resource "aws_security_group_rule" "demo-cluster-ingress-workstation-https" {
  cidr_blocks = [local.workstation-external-cidr]
  description = "Allow workstation to communicate with cluster API Server"
  from_port = 443
  protocol = "tcp"
  security_group_id = aws_security_group.demo-cluster.id
  to_port = 443
  type = "ingress"
}

resource "aws_eks_cluster" "demo" {
  name = var.cluster-name
  role_arn = aws_iam_role.demo-cluster.arn
  vpc_config {
    security_group_ids = [aws_security_group.demo-cluster.id]
    subnet_ids = aws_subnet.demo[*].id
  }
  depends_on = [
    aws_iam_role_policy_attachment.demo-cluster-AmazonEKSClusterPolicy,
    aws_iam_role_policy_attachment.demo-cluster-AmazonEKSVPCResourceController,
  ]
}
```

### ch15/ch15-01/eks-worker-nodes.tf
```hcl
resource "aws_iam_role" "demo-node" {
  name = "terraform-eks-demo-node"
  assume_role_policy = <<POLICY
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "Service": "ec2.amazonaws.com"
      },
      "Action": "sts:AssumeRole"
    }
  ]
}
POLICY
}

resource "aws_iam_role_policy_attachment" "demo-node-AmazonEKSWorkerNodePolicy" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKSWorkerNodePolicy"
  role = aws_iam_role.demo-node.name
}

resource "aws_iam_role_policy_attachment" "demo-node-AmazonEKS_CNI_Policy" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKS_CNI_Policy"
  role = aws_iam_role.demo-node.name
}

resource "aws_iam_role_policy_attachment" "demo-node-AmazonEC2ContainerRegistryReadOnly" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEC2ContainerRegistryReadOnly"
  role = aws_iam_role.demo-node.name
}

resource "aws_eks_node_group" "demo" {
  cluster_name = aws_eks_cluster.demo.name
  node_group_name = "demo"
  node_role_arn = aws_iam_role.demo-node.arn
  subnet_ids = aws_subnet.demo[*].id
  scaling_config {
    desired_size = 1
    max_size = 1
    min_size = 1
  }
  depends_on = [
    aws_iam_role_policy_attachment.demo-node-AmazonEKSWorkerNodePolicy,
    aws_iam_role_policy_attachment.demo-node-AmazonEKS_CNI_Policy,
    aws_iam_role_policy_attachment.demo-node-AmazonEC2ContainerRegistryReadOnly,
  ]
}
```

### ch15/ch15-01/product-deployment.yaml
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: product-deployment
  labels:
    app: product
spec:
  replicas: 1
  selector:
    matchLabels:
      app: product
  template:
    metadata:
      labels:
        app: product
    spec:
      containers:
      - name: product
        image: binildas/product-web
        ports:
        - containerPort: 8080
```

### ch15/ch15-01/product-service.yaml
```yaml
apiVersion: v1
kind: Service
metadata:
  name: product-service-loadbalancer
spec:
  type: LoadBalancer
  selector:
    app: product
  ports:
  - protocol: TCP
    port: 8080
    targetPort: 8080
```

---

## Code Flow Explanation

### Chapter 1 Flow

1. **Application Startup** (`EcomProdMicroApp1.java`)
   - Spring Boot starts the application
   - `@SpringBootApplication` enables auto-configuration

2. **Initialization** (`InitComponent.java`)
   - `@PostConstruct` runs after bean creation
   - InMemoryDB is initialized with sample data

3. **HTTP Request Flow**
   - Browser sends request to `/productsweb`
   - `ProdRestContr.java` handles the request:
     - `getAllProducts()` - GET all products
     - `getProduct(productId)` - GET single product
     - `addProduct()` - POST new product
     - `updateProduct()` - PUT update product
     - `deleteProduct()` - DELETE product

4. **Data Access** (`InMemoryDB.java`)
   - Stores products in `HashMap<String, Product>`
   - Provides CRUD operations
   - Initializes with 2 sample products

5. **Response**
   - Returns JSON data via Spring's `ResponseEntity`
   - UI (`product.html`) displays and manages data

### Chapter 2 Flow

1. **Provider Microservice** (`ProductServer`)
   - MongoDB for persistence
   - `ProductRepository` extends `MongoRepository`
   - REST endpoints at `/products`

2. **Consumer Microservice** (`ProductWeb`)
   - Uses `RestTemplate` for HTTP calls
   - `PRODUCT_SERVICE_URL` points to provider
   - Delegates CRUD operations to provider

3. **Communication**
   - Consumer → Provider: HTTP REST calls
   - Provider → MongoDB: Data operations

### Chapter 3 Flow

1. **Onion Architecture**
   - Domain entities (Product) in center
   - Application services (REST controllers)
   - Infrastructure (Repository, Database)

2. **Hexagonal Architecture**
   - Ports (interfaces) define boundaries
   - Adapters (implementations) for external systems

3. **GraphQL Example**
   - `@QueryMapping` for queries
   - `@SchemaMapping` for nested fields
   - GraphiQL UI at `/graphiql`

### Chapter 5 Flow

1. **Sync Over Async Pattern**
   - Producer sends request with REPLY_TOPIC header
   - Consumer processes and sends response
   - Producer waits (blocks) for response

2. **Partition-Aware Processing**
   - Each consumer instance pinned to specific partition
   - Reply sent to correct partition

3. **Kafka Configuration**
   - `ReplyingKafkaTemplate` for request-reply
   - `CompletableFuture` for async handling

### Chapter 6 Flow

1. **CRUD Over Kafka**
   - `Products` container class with operation types
   - Request: operation + data
   - Response: operation result + data

2. **Aggregator Pattern**
   - Sends same request to multiple services
   - Aggregates responses
   - Times out if not all responses received

### Chapter 7-9 Flow (Docker)

1. **Dockerfile**
   - Multi-stage builds for efficiency
   - JAR copying and execution

2. **Docker Compose**
   - Defines services (web, server, db)
   - Networks and volumes
   - Environment variables

3. **Container Communication**
   - Service names as hostnames
   - Docker networks for isolation

### Chapter 10-11 Flow (Kubernetes)

1. **Deployment**
   - YAML manifests for each service
   - ReplicaSets for scaling
   - Services for networking

2. **StatefulSets**
   - MongoDB with persistent volumes
   - PersistentVolumeClaims for storage

3. **Service Discovery**
   - ClusterIP for internal access
   - NodePort/LoadBalancer for external access

### Chapter 12 Flow (Helm)

1. **Helm Charts**
   - Templates with Go templating
   - Values for configuration
   - Chart.yaml for metadata

2. **Helmfile**
   - Multiple releases in single file
   - Environment-specific values
   - Dependencies management

### Chapter 13 Flow (CI/CD)

1. **Skaffold**
   - Watches source changes
   - Builds images with Jib
   - Deploys to Kubernetes
   - Hot reloads on changes

2. **Development Workflow**
   - `skaffold dev` for development
   - Automatic rebuild on changes
   - Fast feedback loop

### Chapter 14-15 Flow (AWS)

1. **Terraform Infrastructure**
   - VPC, subnets, security groups
   - EC2 instances or EKS clusters
   - IAM roles and policies

2. **Deployment**
   - Build and push Docker images
   - Deploy to AWS infrastructure
   - Access via public IP/hostname

# Comprehensive Interview Guide: Java Microservices and Containers in the Cloud

Based on the book "Java Microservices and Containers in the Cloud" by Binildas A. Christudas, here's a comprehensive guide organized by key concepts with interview Q&A.

---

## 1. Microservices Architecture Fundamentals

### Key Concepts

**Q: What is a microservices architecture and how does it differ from monolithic architecture?**

**A:** Microservices architecture is a distributed approach where applications are built as a collection of small, independent services.

**Monolith vs Microservices:**
- **Monolith**: Single deployable unit, all modules in one codebase, shared database, single technology stack
- **Microservices**: Multiple independent services, separate codebases per service, per-service databases, polyglot technologies

**Key Benefits:**
- Independent deployment and scaling
- Technology diversity
- Fault isolation
- Team autonomy
- Faster time-to-market

---

**Q: Explain the different levels of service granularity.**

**A:** 

1. **Monolith**: All modules packaged in a single .ear/.war
2. **Macroservice**: Separate .jars but deployed together in one node
3. **Mini Service**: Separate archives, but deployed in same node due to constraints
4. **Microservice**: Complete flexibility in development, deployment, and release

> "The true benefits of microservices are reaped when you provide decentralized capability in business transaction processing."

---

**Q: What is the difference between distributed computing and decentralized computing?**

**A:**

| Aspect | Distributed Computing | Decentralized Computing |
|--------|----------------------|------------------------|
| Control | Central coordinator | No central control |
| Decision | Single node decides | Each node decides independently |
| Example | Three-tier architecture | Blockchain, microservices |
| Fault Tolerance | Limited by coordinator | High - no single point of failure |

---

## 2. Hexagonal Architecture (Ports and Adapters)

### Key Concepts

**Q: What is Hexagonal Architecture and why is it important for microservices?**

**A:** Hexagonal Architecture (Ports and Adapters) isolates the application core from external concerns.

**Core Components:**
```
┌─────────────────────────────────────┐
│           Application Core           │
│  (Business Logic, Domain Entities)   │
│                                      │
│  Ports (Interfaces) ←──→ Adapters   │
│       ↓                    ↓         │
│  Driver (UI)         Driven (DB)    │
└─────────────────────────────────────┘
```

**Key Benefits:**
- Business logic is technology-agnostic
- Easy to replace external systems (DB, messaging)
- Testable in isolation
- "Allow an application to equally be driven by users, programs, automated test or batch scripts"

---

**Q: Explain Ports and Adapters with examples.**

**A:**

**Ports** (Interfaces):
- Consumer-agnostic entry/exit points
- In Java: Interface, HTTP REST interface, JMS/AMQP interface
- Example: `ProductRepository` interface

**Adapters** (Implementations):
- Implement the port interface
- Adapt external systems to the application
- Primary (Driving) Adapters: UI, REST Controllers
- Secondary (Driven) Adapters: Database, Message Queue

**Example from Code:**
```java
// Port
public interface ProductRepository extends CrudRepository<ProductOR, Long> {
    List<ProductOR> findByCode(String code);
}

// Adapter (Spring provides the implementation)
// MongoDB or PostgreSQL implementation can be swapped
```

---

## 3. Onion Architecture

### Key Concepts

**Q: How does Onion Architecture complement Hexagonal Architecture?**

**A:** Onion Architecture layers dependencies inward, while Hexagonal Architecture focuses on ports and adapters.

**Onion Layers:**
1. **Domain Model** (Core) - Entities with business logic
2. **Domain Services** - Business logic involving multiple entities
3. **Application Services** - Protocol/format transformations (REST controllers)
4. **Infrastructure** - External concerns (DB, UI, messaging)

**Core Principles:**
- Outer layers depend on inner layers
- Outer layers are transparent to inner layers
- Direction of coupling is toward the center

> "Outer layers depend on inner layers. Outer layers are transparent to inner layers."

---

**Q: Compare Hexagonal and Onion Architectures.**

**A:**

| Aspect | Hexagonal Architecture | Onion Architecture |
|--------|----------------------|-------------------|
| Focus | Ports and Adapters | Layered dependencies |
| Structure | Hexagon with boundaries | Concentric circles |
| Key Concept | Technology isolation | Dependency direction |
| Example Usage | Swapping databases | Reusing business logic |

---

## 4. Eventual Consistency and Distributed Transactions

### Key Concepts

**Q: What is eventual consistency and how does it apply to microservices?**

**A:** Eventual consistency ensures that data becomes consistent across distributed systems over time, rather than immediately (ACID).

**ACID vs BASE:**
- **ACID**: Atomic, Consistent, Isolated, Durable (Traditional)
- **BASE**: Basically Available, Soft-state, Eventually Consistent (Microservices)

**Example - Flight Booking:**
1. Booking microservice creates booking
2. Sends "flight booked" event to messaging channel
3. Inventory microservice consumes event and updates inventory
4. System becomes consistent eventually

**Benefits:**
- Higher availability
- Better fault tolerance
- Loose coupling between services

---

**Q: How do you handle distributed transactions in microservices?**

**A:**

1. **Avoid Distributed Transactions** - Use eventual consistency
2. **Saga Pattern** - Sequence of local transactions
3. **Eventual Consistency** - Use messaging for eventual reconciliation

**Event-Driven Approach:**
```
┌──────────────┐      ┌─────────────────┐      ┌──────────────┐
│    Booking   │─────→│  Message Queue  │─────→│  Inventory   │
│  Microservice│      │   (Kafka)       │      │  Microservice│
└──────────────┘      └─────────────────┘      └──────────────┘
        │                                               │
        ↓                                               ↓
   Booking DB                                       Inventory DB
   (Strongly                                       (Eventually
    Consistent)                                     Consistent)
```

---

## 5. Request-Reply over Async Channels

### Key Concepts

**Q: How do you achieve synchronous request-reply over asynchronous messaging?**

**A:** Using the **Return Address Pattern** with Kafka.

**Pattern:**
1. Producer sends message with `REPLY_TOPIC` header
2. Consumer processes and sends reply to specified topic
3. Producer waits for reply on that topic

**Implementation with Spring Kafka:**
```java
// Producer
ProducerRecord<String, String> record = new ProducerRecord<>(requestTopic, "All");
record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, replyTopic.getBytes()));
RequestReplyFuture<String, String, Products> sendAndReceive = kafkaTemplate.sendAndReceive(record);

// Consumer
@KafkaListener(topics = "${kafka.topic.request-topic}")
@SendTo
public Products listen(String request) {
    return processRequest(request);
}
```

---

**Q: What is the difference between point-to-point and publish-subscribe messaging?**

**A:**

| Aspect | Point-to-Point | Publish-Subscribe |
|--------|---------------|-------------------|
| Delivery | One receiver | Multiple subscribers |
| Scaling | One consumer per message | All subscribers get copy |
| Use Case | Work queues | Event broadcasting |
| Example | HTTP Request | Kafka Topics |

---

## 6. Kafka and Message-Oriented Microservices

### Key Concepts

**Q: How does Kafka support microservices communication?**

**A:** Kafka provides:
- **Pub-Sub** messaging
- **Partitioning** for parallelism
- **Ordering** within partitions
- **Retention** for event replay
- **Consumer Groups** for load balancing

**Architecture:**
```
Producer → Kafka Topic (Partitions) → Consumer Group → Multiple Consumers
```

**Key Features:**
- At-least-once delivery
- Exactly-once semantics
- Durable storage
- High throughput

---

**Q: Explain Kafka partitions and consumer groups.**

**A:**

**Partitions:**
- A topic is divided into partitions
- Each partition maintains message order
- More partitions = higher parallelism

**Consumer Groups:**
- Multiple consumers in same group share partitions
- Each partition assigned to one consumer in group
- Enables horizontal scaling

**Example:**
```
Topic: product-req-topic
  ├── Partition 0 → Consumer Instance 1
  ├── Partition 1 → Consumer Instance 2
  └── Partition 2 → Consumer Instance 3
```

> "The maximum throughput will be achieved when you have one consumer per partition."

---

**Q: How do you handle correlation between requests and responses with multiple instances?**

**A:**

1. **Use unique reply topics** (not elegant - topic management overhead)
2. **Use REPLY_PARTITION header** (better)
3. **Use Correlation ID** (best practice)

**Implementation:**
```java
// Set reply partition
record.headers().add(new RecordHeader(
    KafkaHeaders.REPLY_PARTITION, 
    intToBytesBigEndian(replyPartition)));
```

---

## 7. Docker and Containerization

### Key Concepts

**Q: What are containers and how do they differ from virtual machines?**

**A:**

| Aspect | Virtual Machines | Containers |
|--------|-----------------|------------|
| OS | Full OS per VM | Shared host OS |
| Size | GBs | MBs |
| Startup | Minutes | Seconds |
| Resource | Heavy | Lightweight |
| Isolation | Strong | OS-level |

**Container Architecture:**
```
┌─────────────────────────────────────┐
│           Container                  │
│  ┌─────────────────────────────┐    │
│  │  Application + Dependencies │    │
│  └─────────────────────────────┘    │
│  ┌─────────────────────────────┐    │
│  │   Shared Host OS Kernel     │    │
│  └─────────────────────────────┘    │
└─────────────────────────────────────┘
```

---

**Q: Explain Docker images and layers.**

**A:**

**Docker Images:**
- Read-only templates
- Built in layers
- Each instruction creates a layer
- Layers can be shared

**Layer Example:**
```
┌─────────────────┐
│   Application   │  ← Layer 3 (writable)
├─────────────────┤
│   Dependencies  │  ← Layer 2 (shared)
├─────────────────┤
│   Base OS       │  ← Layer 1 (shared)
└─────────────────┘
```

**Benefits of Layering:**
- Efficient storage (layers shared)
- Fast builds (cached layers)
- Small transfer size

---

**Q: What is a Dockerfile and what are its key instructions?**

**A:**

**Key Instructions:**
```dockerfile
FROM openjdk:8-jdk-alpine   # Base image
VOLUME /tmp                  # Mount point
ARG JAR_FILE                 # Build-time variable
COPY ${JAR_FILE} ecom.jar    # Copy files
ENTRYPOINT ["java","-jar","/ecom.jar"]  # Entry point
```

**Multi-stage Build:**
```dockerfile
FROM maven:3.6.1-jdk-8-slim AS build
RUN mkdir -p /workspace
COPY pom.xml /workspace
RUN mvn clean package

FROM openjdk:8-alpine
COPY --from=build /workspace/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
```

---

## 8. Kubernetes Concepts

### Key Concepts

**Q: What are the main components of Kubernetes architecture?**

**A:**

**Control Plane (Master Nodes):**
1. **API Server** - REST interface for management
2. **Scheduler** - Places pods on nodes
3. **Controller Manager** - Manages controllers
4. **etcd** - Distributed key-value store

**Worker Nodes:**
1. **Kubelet** - Manages containers on node
2. **Kube-proxy** - Network proxy and load balancer
3. **Container Runtime** - Docker, containerd, etc.

**Architecture Diagram:**
```
┌─────────────────────────────────────────────┐
│              Control Plane                   │
│  ┌──────┐  ┌────────┐  ┌────────┐  ┌─────┐ │
│  │ API  │  │Scheduler│  │Controller│ │etcd│ │
│  └──────┘  └────────┘  └────────┘  └─────┘ │
└─────────────────────────────────────────────┘
                    │
┌─────────────────────────────────────────────┐
│              Worker Nodes                    │
│  ┌────────┐  ┌────────┐                    │
│  │ Pod    │  │ Pod    │                    │
│  └────────┘  └────────┘                    │
└─────────────────────────────────────────────┘
```

---

**Q: Explain Pods, Deployments, and Services.**

**A:**

**Pods:**
- Smallest deployable unit
- One or more containers
- Shared network namespace
- Ephemeral (can be replaced)

**Deployments:**
- Manages ReplicaSets
- Declarative updates
- Rollback capability
- Scaling management

**Services:**
- Stable network endpoint
- Service discovery
- Load balancing
- Types: ClusterIP, NodePort, LoadBalancer

---

**Q: How do you expose services in Kubernetes?**

**A:**

| Service Type | Access | Use Case |
|--------------|--------|----------|
| ClusterIP | Internal only | Internal communication |
| NodePort | External via node port | Development/Testing |
| LoadBalancer | External with cloud LB | Production services |
| Ingress | External with routing | HTTP/HTTPS routing |

**Ingress Example:**
```yaml
apiVersion: networking.k8s.io/v1
kind: Ingress
spec:
  rules:
  - host: "products.acme.test"
    http:
      paths:
      - path: /
        backend:
          service:
            name: product-web
            port: 8080
```

---

**Q: What are StatefulSets and Persistent Volumes?**

**A:**

**StatefulSets:**
- For stateful applications (databases)
- Stable network identity
- Stable storage
- Ordered deployment and scaling

**Persistent Volumes (PV):**
- Storage provisioned by administrator
- Independent of pod lifecycle
- Can be retained even if pod is deleted

**Persistent Volume Claims (PVC):**
- Request for storage
- Binds to matching PV
- Decouples pod from storage details

---

## 9. Helm and Automation

### Key Concepts

**Q: What is Helm and why is it important?**

**A:** Helm is Kubernetes' package manager.

**Components:**
1. **Chart** - Package of Kubernetes resources
2. **Config** - Configuration values
3. **Release** - Running instance of a chart

**Benefits:**
- Templating for Kubernetes YAML
- Version management
- Rollback capability
- Parameterization
- Dependency management

---

**Q: Explain Helm templating with an example.**

**A:**

**Helm Template:**
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: {{ include "springboothelm.fullname" . }}
spec:
  replicas: {{ .Values.replicaCount }}
  template:
    spec:
      containers:
      - name: {{ .Chart.Name }}
        image: "{{ .Values.image.repository }}:{{ .Values.image.tag }}"
```

**Values File:**
```yaml
replicaCount: 3
image:
  repository: myapp
  tag: latest
```

**Benefits:**
- Reusable templates
- Environment-specific values
- Consistent deployments

---

**Q: What is Helmfile and how does it differ from Helm?**

**A:**

| Aspect | Helm | Helmfile |
|--------|------|----------|
| Scope | Single chart | Multiple charts |
| Command | helm install | helmfile sync |
| State | Per release | Entire cluster |
| Workflow | Imperative | Declarative |

**Helmfile Example:**
```yaml
repositories:
  - name: stable
    url: https://charts.helm.sh/stable

releases:
  - name: postgres
    chart: ./charts/postgres
    values:
      - ./values/acme-postgres.yaml
  
  - name: product-web
    chart: ./charts/app
    values:
      - ./values/acme-product-web.yaml
```

---

## 10. CI/CD with Skaffold

### Key Concepts

**Q: What is Skaffold and how does it support CI/CD?**

**A:** Skaffold is a tool for continuous development, integration, and delivery of Kubernetes applications.

**Workflow:**
1. Watches source code for changes
2. Builds artifacts (using Jib, Docker, etc.)
3. Tests built artifacts
4. Tags artifacts
5. Pushes to registry
6. Deploys to Kubernetes
7. Monitors deployed artifacts
8. Cleans up on exit

**Skaffold Config:**
```yaml
apiVersion: skaffold/v1beta4
kind: Config
build:
  local:
    push: false
  artifacts:
  - image: myapp
    jibMaven: {}
deploy:
  kubectl:
    manifests:
    - k8s/*.yml
```

---

**Q: What is the difference between Continuous Delivery and Continuous Deployment?**

**A:**

| Aspect | Continuous Delivery | Continuous Deployment |
|--------|---------------------|----------------------|
| Automation | Deploy to staging | Deploy to production |
| Approval | Manual approval needed | Fully automated |
| Use Case | Regulated industries | Fast-moving teams |
| Process | Build → Test → Deploy (Staging) | Build → Test → Deploy (Production) |

---

## 11. Cloud and Infrastructure as Code (IaC)

### Key Concepts

**Q: What are the cloud service models?**

**A:**

| Model | You Manage | Provider Manages | Example |
|-------|-----------|------------------|---------|
| IaaS | Apps, Data, Runtime, OS | Storage, Network, Servers | EC2 |
| PaaS | Apps, Data | Runtime, OS, Storage, Servers | Elastic Beanstalk |
| SaaS | Nothing | Everything | Salesforce |

---

**Q: What is Infrastructure as Code (IaC) and why is it important?**

**A:** IaC is managing infrastructure through code instead of manual processes.

**Benefits:**
1. **Self-service** - Developers can provision infrastructure
2. **Version control** - Infrastructure changes tracked
3. **Consistency** - Repeatable deployments
4. **Automation** - No manual steps
5. **Documentation** - Code is documentation

---

**Q: Explain Terraform with examples.**

**A:** Terraform is an open-source IaC tool from HashiCorp.

**Provider Configuration:**
```hcl
provider "aws" {
  region = "ap-southeast-1"
}
```

**Resource Definition:**
```hcl
resource "aws_vpc" "test-env" {
  cidr_block = "10.0.0.0/16"
  tags = {
    Name = "my-vpc"
  }
}

resource "aws_instance" "test-ec2-instance" {
  ami = "ami-061058b2c8f7fb264"
  instance_type = "t2.micro"
}
```

**Workflow:**
1. `terraform init` - Initialize configuration
2. `terraform plan` - Preview changes
3. `terraform apply` - Apply changes
4. `terraform destroy` - Destroy resources

---

## 12. AWS Specific Concepts

### Key Concepts

**Q: What is AWS EKS and how does it differ from self-managed Kubernetes?**

**A:**

| Aspect | Self-Managed | AWS EKS |
|--------|-------------|---------|
| Control Plane | You manage | AWS manages |
| Updates | Manual | Automated |
| Availability | You manage | Multi-AZ |
| Integration | Manual | Native with AWS |
| Cost | Infrastructure only | Infrastructure + EKS fee |

---

**Q: What is the difference between VPC, Subnet, and Security Groups?**

**A:**

**VPC (Virtual Private Cloud):**
- Logical isolation in AWS Cloud
- Private network for resources
- Range: /16 (65,536 IPs)

**Subnet:**
- Range of IPs within VPC
- /24 (256 IPs)
- Public or Private

**Security Groups:**
- Virtual firewall for instances
- Instance-level control
- Stateful (inbound rules affect outbound)

---

**Q: What are the key AWS services used in the book?**

**A:**

| Service | Purpose |
|---------|---------|
| VPC | Network isolation |
| EC2 | Virtual machines |
| EKS | Managed Kubernetes |
| S3 | Object storage |
| IAM | Access management |
| RDS | Managed databases |

---

## 13. Messaging and Integration Patterns

### Key Concepts

**Q: What is the Aggregator integration pattern?**

**A:** Aggregator combines responses from multiple services into a single response.

**Use Case:** 
- Sending same request to multiple providers
- Waiting for responses with timeout
- Combining results

**Implementation:**
```java
AggregatingReplyingKafkaTemplate<K, V, R> template = new AggregatingReplyingKafkaTemplate<>(
    pf, replyContainer,
    (list, timeout) -> (list.size() == numProviders) || (timeout)
);
```

---

**Q: What is the difference between RPC and Messaging patterns?**

**A:**

| Aspect | RPC | Messaging |
|--------|-----|-----------|
| Synchrony | Synchronous | Asynchronous |
| Coupling | Tight | Loose |
| Fault Handling | Complex | Graceful |
| Scaling | Limited | High |
| Use Case | Request-Response | Event-driven |

---

## 14. Best Practices and Design Patterns

### Key Concepts

**Q: What are the key design principles for microservices?**

**A:**

1. **Single Responsibility** - Each service does one thing
2. **Domain-driven Design** - Bounded contexts
3. **Database per Service** - Data isolation
4. **Statelessness** - Scale horizontally
5. **API-first Design** - Contracts first
6. **Asynchronous Communication** - Event-driven
7. **Automation** - CI/CD pipelines
8. **Observability** - Logging, metrics, tracing

---

**Q: How do you handle scalability in microservices?**

**A:**

**Types of Scaling:**
1. **Vertical Scaling** - More CPU/RAM to existing instance
2. **Horizontal Scaling** - More instances (preferred)

**Scaling Strategies:**
```
┌─────────────────────────────────────┐
│        Microservice Scaling          │
├─────────────────────────────────────┤
│  Vertical Growth                     │
│  ┌──────┐  ┌──────┐  ┌──────┐      │
│  │Func1 │  │Func2 │  │Func3 │      │
│  └──────┘  └──────┘  └──────┘      │
│  (Endlessly adding functions)       │
├─────────────────────────────────────┤
│  Horizontal Growth                  │
│  ┌──────┐  ┌──────┐                 │
│  │Service│  │Service│                 │
│  │  A    │  │  B    │                 │
│  └──────┘  └──────┘                 │
├─────────────────────────────────────┤
│  Scale Out                         │
│  ┌──────┐  ┌──────┐  ┌──────┐      │
│  │Instance│  │Instance│  │Instance│  │
│  │   1   │  │   2   │  │   3   │   │
│  └──────┘  └──────┘  └──────┘      │
└─────────────────────────────────────┘
```

---

**Q: What is the SEDA (Staged Event-Driven Architecture) pattern?**

**A:** SEDA is an architecture pattern for well-conditioned, scalable services.

**Components:**
1. **Stages** - Processing units
2. **Queues** - Communication between stages
3. **Thread Pools** - Resource management
4. **Controllers** - Dynamic resource allocation

**Benefits:**
- Load conditioning
- Resource isolation
- Dynamic scaling
- Backpressure handling

---

## 15. Database Integration

### Key Concepts

**Q: Compare MongoDB and PostgreSQL in microservices context.**

**A:**

| Aspect | MongoDB | PostgreSQL |
|--------|---------|------------|
| Type | NoSQL Document | Relational |
| Schema | Flexible | Rigid |
| Scaling | Horizontal easy | Vertical focused |
| Transactions | Limited | ACID |
| Use Case | Rapid iteration | Complex queries |
| Query Language | MongoDB Query | SQL |

---

**Q: What is Liquibase and why is it used?**

**A:** Liquibase is a database migration tool.

**Features:**
- Version control for database schema
- Track changes (ChangeLog)
- Support for multiple databases
- Automated migrations
- Rollback capability

**ChangeLog Example:**
```xml
<changeSet id="1" author="Binildas">
    <sqlFile path="01_init_product.sql" 
             relativeToChangeLogFile="true"/>
</changeSet>
```

---

## 16. Advanced Concepts

### Key Concepts

**Q: What is HATEOAS and how does it help REST APIs?**

**A:** HATEOAS (Hypermedia as the Engine of Application State) provides context-driven responses using hypermedia controls.

**Example Response:**
```json
{
  "productId": "1",
  "name": "Product",
  "_links": {
    "self": { "href": "/products/1" },
    "buy": { "href": "/products/1/buy" }
  }
}
```

**Benefits:**
- API discoverability
- Reduced coupling
- State-driven navigation
- Self-documenting APIs

---

**Q: What is GraphQL and when would you use it?**

**A:** GraphQL is a query language for APIs.

**REST vs GraphQL:**
```
REST: Multiple endpoints, over-fetching
/products - gets all fields
/products/1 - gets all fields

GraphQL: Single endpoint, exactly what you need
query {
  products(count: 2) {
    productId
    code
    price
  }
}
```

**Benefits:**
- Client-defined queries
- No over-fetching
- Nested relationships
- Strong typing

---

**Q: Explain eventual consistency and transaction boundaries in microservices.**

**A:**

**Transaction Boundaries:**
1. **Local Transactions** - Within a single service
2. **Distributed Transactions** - Across services (avoid)
3. **Eventual Consistency** - Settled over time

**Patterns for Consistency:**
- Saga Pattern
- Event Sourcing
- CQRS
- Two-Phase Commit (discouraged)

```
┌─────────────────────────────────────────────┐
│         Eventual Consistency Flow           │
├─────────────────────────────────────────────┤
│  1. Service A creates data                  │
│  2. Service A sends event                   │
│  3. Event is stored in event store          │
│  4. Service B processes event               │
│  5. Service B updates its data              │
│  6. Data is eventually consistent           │
└─────────────────────────────────────────────┘
```

---

## Quick Reference: Key Commands

### Docker Commands
```bash
docker build -t image-name .
docker run -it -p 8080:8080 image-name
docker ps -a
docker logs container-id
docker-compose up -d
docker-compose down
```

### Kubernetes Commands
```bash
kubectl apply -f deployment.yml
kubectl get pods
kubectl logs pod-name
kubectl describe pod pod-name
kubectl delete -f deployment.yml
```

### Helm Commands
```bash
helm create chart-name
helm install release-name chart-name
helm upgrade release-name chart-name
helm rollback release-name revision
helm delete release-name
helmfile sync
```

### Terraform Commands
```bash
terraform init
terraform plan
terraform apply
terraform destroy
```

---

## Key Takeaways for Interviews

1. **Understand the "Why"** - Know why patterns are used, not just what they are
2. **Architecture Focus** - Emphasize Hexagonal/Onion architecture benefits
3. **Distributed Systems** - Explain eventual consistency and why ACID isn't always suitable
4. **Practical Experience** - Reference code examples from the book
5. **Cloud-Native** - Discuss containers, orchestration, and CI/CD
6. **Problem-Solving** - Explain how patterns solve specific problems
7. **Trade-offs** - Acknowledge trade-offs in different approaches

> "Microservices promise the holy grail: concurrent releases and selective scalability." - Binildas A. Christudas

# Complete Code Extraction from "Beginning Spring 6"

Based on the book content, I'll extract and organize all the code examples chapter by chapter. Here's the complete code structure:

## Project Root Structure

```
Beginning-Spring-6/
├── pom.xml
├── chapter02/
├── chapter03/
├── chapter04/
├── chapter05-api/
├── chapter05-anno/
├── chapter05-xml/
├── chapter06/
├── chapter07/
├── chapter08/
├── chapter09-api/
├── chapter09-test/
├── chapter09-jpa/
├── chapter09-mongo/
├── chapter10/
├── chapter10-custom/
├── chapter10-jpa/
├── chapter10-oauth/
├── chapter11-batch/
└── chapter11-modulith/
```

---

## Root pom.xml (Main Project)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.apress</groupId>
    <artifactId>bsg6</artifactId>
    <version>1.0</version>
    <packaging>pom</packaging>

    <modules>
        <module>chapter02</module>
        <module>chapter03</module>
        <module>chapter04</module>
        <module>chapter05-api</module>
        <module>chapter05-anno</module>
        <module>chapter05-xml</module>
        <module>chapter06</module>
        <module>chapter07</module>
        <module>chapter08</module>
        <module>chapter09-api</module>
        <module>chapter09-test</module>
        <module>chapter09-jpa</module>
        <module>chapter09-mongo</module>
        <module>chapter10</module>
        <module>chapter10-custom</module>
        <module>chapter10-jpa</module>
        <module>chapter10-oauth</module>
        <module>chapter11-batch</module>
        <module>chapter11-modulith</module>
    </modules>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <testngVersion>7.8.0</testngVersion>
        <springFrameworkVersion>6.1.1</springFrameworkVersion>
        <springBootVersion>3.1.1</springBootVersion>
        <springDataBomVersion>2023.0.1</springDataBomVersion>
        <jacksonVersion>2.15.0</jacksonVersion>
        <h2Version>2.1.214</h2Version>
        <logbackVersion>1.4.8</logbackVersion>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${springBootVersion}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>org.testng</groupId>
                <artifactId>testng</artifactId>
                <version>${testngVersion}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
        </dependency>
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.1.2</version>
                <dependencies>
                    <dependency>
                        <groupId>org.apache.maven.surefire</groupId>
                        <artifactId>surefire-testng</artifactId>
                        <version>3.1.2</version>
                    </dependency>
                </dependencies>
            </plugin>
        </plugins>
    </build>
</project>
```

---

## Chapter 2: Hello, World!

### chapter02/pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.apress</groupId>
        <artifactId>bsg6</artifactId>
        <version>1.0</version>
    </parent>
    <artifactId>chapter02</artifactId>
    <version>1.0</version>
</project>
```

### chapter02/src/main/java/com/bsg6/chapter02/Greeter.java
```java
package com.bsg6.chapter02;

import java.io.PrintStream;

public interface Greeter {
    void setPrintStream(PrintStream printStream);
    void greet();
}
```

### chapter02/src/main/java/com/bsg6/chapter02/HelloWorldGreeter.java
```java
package com.bsg6.chapter02;

import java.io.PrintStream;

public class HelloWorldGreeter implements Greeter {
    private PrintStream printStream = System.out;

    @Override
    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void greet() {
        printStream.print("Hello, World!");
    }
}
```

### chapter02/src/test/java/com/bsg6/chapter02/GreeterTest.java
```java
package com.bsg6.chapter02;

import org.testng.annotations.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import static org.testng.Assert.assertEquals;

public class GreeterTest {
    @Test
    public void testHelloWorld() {
        Greeter greeter = new HelloWorldGreeter();
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PrintStream ps = new PrintStream(baos, true, StandardCharsets.UTF_8)) {
            greeter.setPrintStream(ps);
            greeter.greet();
        }
        String data = baos.toString(StandardCharsets.UTF_8);
        assertEquals(data, "Hello, World!");
    }
}
```

### chapter02/src/main/resources/applicationContext.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans 
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="helloGreeter" class="com.bsg6.chapter02.HelloWorldGreeter">
        <property name="printStream" ref="printStream"/>
    </bean>

    <bean id="printStream" class="java.io.PrintStream">
        <constructor-arg ref="baos"/>
        <constructor-arg value="true"/>
        <constructor-arg value="UTF-8"/>
    </bean>

    <bean id="baos" class="java.io.ByteArrayOutputStream"/>
</beans>
```

### chapter02/src/test/java/com/bsg6/chapter02/SpringGreeterTest.java
```java
package com.bsg6.chapter02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import static org.testng.Assert.assertEquals;

public class SpringGreeterTest {
    @Test
    public void testHelloWorld() {
        ApplicationContext context = 
            new ClassPathXmlApplicationContext("applicationContext.xml");
        Greeter greeter = context.getBean("helloGreeter", Greeter.class);
        ByteArrayOutputStream baos = context.getBean("baos", ByteArrayOutputStream.class);
        greeter.greet();
        String data = baos.toString(StandardCharsets.UTF_8);
        assertEquals(data, "Hello, World!");
    }
}
```

---

## Chapter 3: Configuration and Declaration of Beans

### chapter03/pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.apress</groupId>
        <artifactId>bsg6</artifactId>
        <version>1.0</version>
    </parent>
    <artifactId>chapter03</artifactId>
    <version>1.0</version>
</project>
```

### chapter03/src/main/java/com/bsg6/chapter03/model/Song.java
```java
package com.bsg6.chapter03.model;

import java.util.Objects;

public class Song implements Comparable<Song> {
    private String name;
    private int votes;

    public Song() {}

    public Song(String name) {
        setName(name);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getVotes() { return votes; }
    public void setVotes(int votes) { this.votes = votes; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        Song song = (Song) o;
        return Objects.equals(getName(), song.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Song[" + "name='" + name + "', votes=" + votes + "]";
    }

    @Override
    public int compareTo(Song o) {
        int value = Integer.compare(o.getVotes(), getVotes());
        if (value == 0) {
            value = getName().compareTo(o.getName());
        }
        return value;
    }
}
```

### chapter03/src/main/java/com/bsg6/chapter03/model/Artist.java
```java
package com.bsg6.chapter03.model;

import java.util.*;

public class Artist {
    private String name;
    private Map<String, Song> songs = new HashMap<>();

    public Artist() {}

    public Artist(String name) {
        setName(name);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Map<String, Song> getSongs() { return songs; }
    public void setSongs(Map<String, Song> songs) { this.songs = songs; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;
        Artist artist = (Artist) o;
        return Objects.equals(getName(), artist.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Artist.class.getName() + "[", "]")
                .add("name=" + name)
                .add("songs=" + songs)
                .toString();
    }
}
```

### chapter03/src/main/java/com/bsg6/chapter03/Normalizer.java
```java
package com.bsg6.chapter03;

public interface Normalizer {
    default String transform(String input) {
        return input.trim();
    }
}
```

### chapter03/src/main/java/com/bsg6/chapter03/MusicService.java
```java
package com.bsg6.chapter03;

import com.bsg6.chapter03.model.Song;
import java.util.List;

public interface MusicService {
    List<Song> getSongsForArtist(String artist);
    List<String> getMatchingSongNamesForArtist(String artist, String prefix);
    List<String> getMatchingArtistNames(String prefix);
    Song getSong(String artist, String name);
    Song voteForSong(String artist, String name);
}
```

### chapter03/src/main/java/com/bsg6/chapter03/Resettable.java
```java
package com.bsg6.chapter03;

public interface Resettable {
    void reset();
}
```

### chapter03/src/main/java/com/bsg6/chapter03/AbstractMusicService.java
```java
package com.bsg6.chapter03;

import com.bsg6.chapter03.model.Artist;
import com.bsg6.chapter03.model.Song;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractMusicService implements MusicService, Resettable {
    private Map<String, Artist> bands = new HashMap<>();

    protected String transformArtist(String input) { return input; }
    protected String transformSong(String input) { return input; }

    @Override
    public void reset() {
        bands.clear();
    }

    private Artist getArtist(String name) {
        String normalizedName = transformArtist(name);
        return bands.computeIfAbsent(normalizedName, Artist::new);
    }

    @Override
    public Song getSong(String artistName, String name) {
        Artist artist = getArtist(artistName);
        String normalizedTitle = transformSong(name);
        return artist.getSongs().computeIfAbsent(normalizedTitle, Song::new);
    }

    @Override
    public List<Song> getSongsForArtist(String artist) {
        List<Song> songs = new ArrayList<>(getArtist(artist).getSongs().values());
        songs.sort(Song::compareTo);
        return songs;
    }

    @Override
    public List<String> getMatchingSongNamesForArtist(String artist, String prefix) {
        String normalizedPrefix = transformSong(prefix).toLowerCase();
        return getArtist(artist)
                .getSongs()
                .keySet()
                .stream()
                .map(this::transformSong)
                .filter(name -> name.toLowerCase().startsWith(normalizedPrefix))
                .sorted(Comparator.comparing(Function.identity()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getMatchingArtistNames(String prefix) {
        String normalizedPrefix = transformArtist(prefix).toLowerCase();
        return bands
                .keySet()
                .stream()
                .filter(name -> name.toLowerCase().startsWith(normalizedPrefix))
                .sorted(Comparator.comparing(Function.identity()))
                .collect(Collectors.toList());
    }

    @Override
    public Song voteForSong(String artistName, String name) {
        Song song = getSong(artistName, name);
        song.setVotes(song.getVotes() + 1);
        return song;
    }
}
```

### chapter03/src/main/java/com/bsg6/chapter03/mem01/MusicService1.java
```java
package com.bsg6.chapter03.mem01;

import com.bsg6.chapter03.AbstractMusicService;
import org.springframework.stereotype.Component;

@Component
public class MusicService1 extends AbstractMusicService {
}
```

### chapter03/src/test/resources/config-01.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">
    <context:component-scan base-package="com.bsg6.chapter03.mem01" />
</beans>
```

### chapter03/src/test/java/com/bsg6/chapter03/TestMusicService1.java
```java
package com.bsg6.chapter03;

import com.bsg6.chapter03.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import static org.testng.Assert.*;

@ContextConfiguration(locations = "/config-01.xml")
public class TestMusicService1 extends AbstractTestNGSpringContextTests {
    @Autowired ApplicationContext context;
    @Autowired MusicService service;

    @Test
    public void testConfiguration() {
        assertNotNull(context);
        Set<String> definitions = new HashSet<>(Arrays.asList(context.getBeanDefinitionNames()));
        assertTrue(definitions.contains("musicService1"));
    }

    @Test
    public void testMusicService() {
        assertNotNull(service);
        Song song = service.getSong("Threadbare Loaf", "Someone Stole the Flour");
        assertNotNull(song);
        assertEquals(song.getVotes(), 0);
    }
}
```

### chapter03/src/main/java/com/bsg6/chapter03/mem02/MusicService2.java
```java
package com.bsg6.chapter03.mem02;

import com.bsg6.chapter03.AbstractMusicService;
import com.bsg6.chapter03.Normalizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MusicService2 extends AbstractMusicService {
    @Autowired
    Normalizer normalizer;

    @Override
    protected String transformArtist(String input) {
        return normalizer.transform(input);
    }

    @Override
    protected String transformSong(String input) {
        return normalizer.transform(input);
    }
}
```

### chapter03/src/main/java/com/bsg6/chapter03/mem02/SimpleNormalizer.java
```java
package com.bsg6.chapter03.mem02;

import com.bsg6.chapter03.Normalizer;
import org.springframework.stereotype.Component;

@Component
public class SimpleNormalizer implements Normalizer {
    // inherits default transform() method from interface
}
```

### chapter03/src/test/resources/config-02.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">
    <context:component-scan base-package="com.bsg6.chapter03.mem02" />
</beans>
```

### chapter03/src/test/java/com/bsg6/chapter03/MusicServiceTests.java
```java
package com.bsg6.chapter03;

import com.bsg6.chapter03.model.Song;
import java.util.List;
import java.util.function.Consumer;
import static org.testng.Assert.assertEquals;

public class MusicServiceTests {
    private Object[][] model = new Object[][]{
        {"Threadbare Loaf", "Someone Stole the Flour", 4},
        {"Threadbare Loaf", "What Happened To Our First CD?", 17},
        {"Therapy Zeppelin", "Medium", 4},
        {"Clancy in Silt", "Igneous", 5}
    };

    void iterateOverModel(Consumer<Object[]> consumer) {
        for (Object[] data : model) {
            consumer.accept(data);
        }
    }

    void populateService(MusicService service) {
        iterateOverModel(data -> {
            for (int i = 0; i < (Integer) data[2]; i++) {
                service.voteForSong((String) data[0], (String) data[1]);
            }
        });
    }

    void reset(MusicService service) {
        if (service instanceof Resettable) {
            ((Resettable) service).reset();
        } else {
            throw new RuntimeException(service + " does not implement Resettable.");
        }
    }

    void testSongVoting(MusicService service) {
        reset(service);
        populateService(service);
        iterateOverModel(data ->
            assertEquals(
                service.getSong((String) data[0], (String) data[1]).getVotes(),
                ((Integer) data[2]).intValue()
            ));
    }

    void testSongsForArtist(MusicService service) {
        reset(service);
        populateService(service);
        List<Song> songs = service.getSongsForArtist("Threadbare Loaf");
        assertEquals(songs.size(), 2);
        assertEquals(songs.get(0).getName(), "What Happened To Our First CD?");
        assertEquals(songs.get(0).getVotes(), 17);
        assertEquals(songs.get(1).getName(), "Someone Stole the Flour");
        assertEquals(songs.get(1).getVotes(), 4);
    }

    void testMatchingArtistNames(MusicService service) {
        reset(service);
        populateService(service);
        List<String> names = service.getMatchingArtistNames("Th");
        assertEquals(names.size(), 2);
        assertEquals(names.get(0), "Therapy Zeppelin");
        assertEquals(names.get(1), "Threadbare Loaf");
    }

    void testMatchingSongNamesForArtist(MusicService service) {
        reset(service);
        populateService(service);
        List<String> names = service.getMatchingSongNamesForArtist("Threadbare Loaf", "W");
        assertEquals(names.size(), 1);
        assertEquals(names.get(0), "What Happened To Our First CD?");
    }
}
```

### chapter03/src/test/java/com/bsg6/chapter03/TestMusicService2.java
```java
package com.bsg6.chapter03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "/config-02.xml")
public class TestMusicService2 extends AbstractTestNGSpringContextTests {
    @Autowired MusicService service;
    MusicServiceTests tests = new MusicServiceTests();

    @Test public void testSongVoting() { tests.testSongVoting(service); }
    @Test public void testGetMatchingArtistNames() { tests.testMatchingArtistNames(service); }
    @Test public void testGetSongsForArtist() { tests.testSongsForArtist(service); }
    @Test public void testMatchingSongNamesForArtist() { tests.testMatchingSongNamesForArtist(service); }
}
```

### chapter03/src/main/java/com/bsg6/chapter03/mem03/SimpleNormalizer.java
```java
package com.bsg6.chapter03.mem03;

import com.bsg6.chapter03.Normalizer;
import org.springframework.stereotype.Component;

@Component("foo")
public class SimpleNormalizer implements Normalizer {
}
```

### chapter03/src/main/java/com/bsg6/chapter03/mem03/CapLeadingNormalizer.java
```java
package com.bsg6.chapter03.mem03;

import com.bsg6.chapter03.Normalizer;
import org.springframework.stereotype.Component;
import java.util.StringJoiner;
import java.util.stream.Stream;

@Component("bar")
public class CapLeadingNormalizer implements Normalizer {
    @Override
    public String transform(String input) {
        StringJoiner joiner = new StringJoiner(" ");
        Stream.of(input.trim().split("\\s"))
              .filter(s -> !s.isBlank())
              .map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase())
              .forEach(joiner::add);
        return joiner.toString();
    }
}
```

### chapter03/src/test/java/com/bsg6/chapter03/TestCapLeadingNormalizer.java
```java
package com.bsg6.chapter03;

import com.bsg6.chapter03.mem03.CapLeadingNormalizer;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class TestCapLeadingNormalizer {
    Normalizer normalizer = new CapLeadingNormalizer();

    @DataProvider
    Object[][] data() {
        return new Object[][]{
            {"this is a test", "This Is A Test"},
            {" This IS a test ", "This Is A Test"},
            {"this is a test", "This Is A Test"}
        };
    }

    @Test(dataProvider = "data")
    public void testTransform(String input, String expected) {
        assertEquals(normalizer.transform(input), expected);
    }
}
```

### chapter03/src/main/java/com/bsg6/chapter03/mem03/MusicService3.java
```java
package com.bsg6.chapter03.mem03;

import com.bsg6.chapter03.AbstractMusicService;
import com.bsg6.chapter03.Normalizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MusicService3 extends AbstractMusicService {
    @Autowired @Qualifier("bar") Normalizer artistNormalizer;
    @Autowired @Qualifier("foo") Normalizer songNormalizer;

    public Normalizer getArtistNormalizer() { return artistNormalizer; }
    public void setArtistNormalizer(Normalizer artistNormalizer) { this.artistNormalizer = artistNormalizer; }
    public Normalizer getSongNormalizer() { return songNormalizer; }
    public void setSongNormalizer(Normalizer songNormalizer) { this.songNormalizer = songNormalizer; }

    @Override
    protected String transformArtist(String input) {
        return artistNormalizer.transform(input);
    }

    @Override
    protected String transformSong(String input) {
        return songNormalizer.transform(input);
    }
}
```

### chapter03/src/test/resources/config-03.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">
    <context:component-scan base-package="com.bsg6.chapter03.mem03" />
</beans>
```

### chapter03/src/test/java/com/bsg6/chapter03/TestMusicService3.java
```java
package com.bsg6.chapter03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "/config-03.xml")
public class TestMusicService3 extends AbstractTestNGSpringContextTests {
    @Autowired MusicService service;
    MusicServiceTests tests = new MusicServiceTests();

    @Test public void testSongVoting() { tests.testSongVoting(service); }
    @Test public void testGetMatchingArtistNames() { tests.testMatchingArtistNames(service); }
    @Test public void testGetSongsForArtist() { tests.testSongsForArtist(service); }
    @Test public void testMatchingSongNamesForArtist() { tests.testMatchingSongNamesForArtist(service); }
}
```

### chapter03/src/test/resources/musicservicetest.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="musicServiceTests" class="com.bsg6.chapter03.MusicServiceTests" />
</beans>
```

### chapter03/src/test/resources/normalizers.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">
    <context:component-scan base-package="com.bsg6.chapter03.mem03" resource-pattern="*Normalizer.class"/>
</beans>
```

### chapter03/src/test/resources/config-04.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">
    <import resource="normalizers.xml"/>
    <import resource="musicservicetest.xml"/>
    <context:component-scan base-package="com.bsg6.chapter03.mem04"/>
</beans>
```

### chapter03/src/test/java/com/bsg6/chapter03/TestConfigurationImport.java
```java
package com.bsg6.chapter03;

import com.bsg6.chapter03.mem03.CapLeadingNormalizer;
import com.bsg6.chapter03.mem03.SimpleNormalizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

@ContextConfiguration(locations = "/config-04.xml")
public class TestConfigurationImport extends AbstractTestNGSpringContextTests {
    @Autowired ApplicationContext context;

    @DataProvider
    Object[][] resources() {
        return new Object[][]{
            {"musicServiceTests"},
            {MusicServiceTests.class},
            {"foo"},
            {"bar"},
            {SimpleNormalizer.class},
            {CapLeadingNormalizer.class},
            {"musicService4"}
        };
    }

    @Test(dataProvider = "resources")
    public void validateResourceExistence(Object resource) {
        if (resource instanceof String) {
            assertNotNull(context.getBean(resource.toString()));
        } else if (resource instanceof Class<?>) {
            assertNotNull(context.getBean((Class<?>) resource));
        } else {
            fail("Invalid resource type");
        }
    }
}
```

### chapter03/src/main/java/com/bsg6/chapter03/mem04/MusicService4.java
```java
package com.bsg6.chapter03.mem04;

import com.bsg6.chapter03.AbstractMusicService;
import com.bsg6.chapter03.Normalizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MusicService4 extends AbstractMusicService {
    private final Normalizer artistNormalizer;
    private final Normalizer songNormalizer;

    public MusicService4(@Autowired @Qualifier("bar") Normalizer artistNormalizer,
                         @Autowired @Qualifier("foo") Normalizer songNormalizer) {
        this.artistNormalizer = artistNormalizer;
        this.songNormalizer = songNormalizer;
    }

    @Override
    protected String transformArtist(String input) {
        return artistNormalizer.transform(input);
    }

    @Override
    protected String transformSong(String input) {
        return songNormalizer.transform(input);
    }
}
```

### chapter03/src/test/java/com/bsg6/chapter03/TestMusicService4.java
```java
package com.bsg6.chapter03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "/config-04.xml")
public class TestMusicService4 extends AbstractTestNGSpringContextTests {
    @Autowired MusicService service;
    @Autowired MusicServiceTests tests;

    @Test public void testSongVoting() { tests.testSongVoting(service); }
    @Test public void testGetMatchingArtistNames() { tests.testMatchingArtistNames(service); }
    @Test public void testGetSongsForArtist() { tests.testSongsForArtist(service); }
    @Test public void testMatchingSongNamesForArtist() { tests.testMatchingSongNamesForArtist(service); }
}
```

### chapter03/src/test/resources/normalizers-na.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="bar" class="com.bsg6.chapter03.mem03.SimpleNormalizer" />
    <bean id="foo" class="com.bsg6.chapter03.mem03.CapLeadingNormalizer" />
</beans>
```

### chapter03/src/test/resources/config-05.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">
    <import resource="normalizers-na.xml" />
    <bean name="musicService" class="com.bsg6.chapter03.mem03.MusicService3">
        <property name="artistNormalizer" ref="foo"/>
        <property name="songNormalizer" ref="bar"/>
    </bean>
</beans>
```

### chapter03/src/test/java/com/bsg6/chapter03/TestMusicService5.java
```java
package com.bsg6.chapter03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = {"config-05.xml","/musicservicetest.xml"})
public class TestMusicService5 extends AbstractTestNGSpringContextTests {
    @Autowired MusicService service;
    @Autowired MusicServiceTests tests;

    @Test public void testSongVoting(){ tests.testSongVoting(service); }
    @Test public void testGetMatchingArtistNames(){ tests.testMatchingArtistNames(service); }
    @Test public void testGetSongsForArtist(){ tests.testSongsForArtist(service); }
    @Test public void testMatchingSongNamesForArtist(){ tests.testMatchingSongNamesForArtist(service); }
}
```

### chapter03/src/test/resources/config-06.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">
    <import resource="/normalizers-na.xml"/>
    <bean name="musicService" class="com.bsg6.chapter03.mem04.MusicService4">
        <constructor-arg name="artistNormalizer" ref="foo"/>
        <constructor-arg name="songNormalizer" ref="bar"/>
    </bean>
</beans>
```

### chapter03/src/test/java/com/bsg6/chapter03/TestMusicService6.java
```java
package com.bsg6.chapter03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = {"config-06.xml","/musicservicetest.xml"})
public class TestMusicService6 extends AbstractTestNGSpringContextTests {
    @Autowired MusicService service;
    @Autowired MusicServiceTests tests;

    @Test public void testSongVoting(){ tests.testSongVoting(service); }
    @Test public void testGetMatchingArtistNames(){ tests.testMatchingArtistNames(service); }
    @Test public void testGetSongsForArtist(){ tests.testSongsForArtist(service); }
    @Test public void testMatchingSongNamesForArtist(){ tests.testMatchingSongNamesForArtist(service); }
}
```

### chapter03/src/test/java/com/bsg6/chapter03/Configuration7.java
```java
package com.bsg6.chapter03;

import com.bsg6.chapter03.mem01.MusicService1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuration7 {
    @Bean
    MusicService musicService() {
        return new MusicService1();
    }
}
```

### chapter03/src/test/java/com/bsg6/chapter03/TestConfiguration.java
```java
package com.bsg6.chapter03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {
    @Bean
    MusicServiceTests musicServiceTests() {
        return new MusicServiceTests();
    }
}
```

### chapter03/src/test/java/com/bsg6/chapter03/MusicServiceRunner.java
```java
package com.bsg6.chapter03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MusicServiceRunner {
    public static void main(String[] args) {
        Class<?>[] configurations = new Class<?>[]{Configuration7.class, TestConfiguration.class};
        ApplicationContext context = new AnnotationConfigApplicationContext(configurations);
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
    }
}
```

### chapter03/src/test/java/com/bsg6/chapter03/Configuration8.java
```java
package com.bsg6.chapter03;

import com.bsg6.chapter03.mem02.MusicService2;
import com.bsg6.chapter03.mem02.SimpleNormalizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuration8 {
    @Bean
    Normalizer normalizer() {
        return new SimpleNormalizer();
    }

    @Bean
    MusicService musicService() {
        return new MusicService2();
    }
}
```

### chapter03/src/test/java/com/bsg6/chapter03/Configuration9.java
```java
package com.bsg6.chapter03;

import com.bsg6.chapter03.mem03.CapLeadingNormalizer;
import com.bsg6.chapter03.mem03.SimpleNormalizer;
import com.bsg6.chapter03.mem03.MusicService3;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuration9 {
    @Bean
    Normalizer foo() {
        return new SimpleNormalizer();
    }

    @Bean(name="bar")
    Normalizer capNormalizer() {
        return new CapLeadingNormalizer();
    }

    @Bean
    MusicService musicService() {
        return new MusicService3();
    }
}
```

### chapter03/src/test/java/com/bsg6/chapter03/Configuration10.java
```java
package com.bsg6.chapter03;

import com.bsg6.chapter03.mem03.CapLeadingNormalizer;
import com.bsg6.chapter03.mem03.SimpleNormalizer;
import com.bsg6.chapter03.mem04.MusicService4;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuration10 {
    @Bean
    Normalizer foo(){
        return new SimpleNormalizer();
    }

    @Bean
    Normalizer bar(){
        return new CapLeadingNormalizer();
    }

    @Bean
    MusicService musicService(Normalizer bar, @Qualifier("foo") Normalizer baz){
        return new MusicService4(bar, baz);
    }
}
```

### chapter03/src/test/java/com/bsg6/chapter03/TestMusicService10.java
```java
package com.bsg6.chapter03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.function.Consumer;

@ContextConfiguration(classes = {TestConfiguration.class})
public class TestMusicService10 extends AbstractTestNGSpringContextTests {
    @Autowired MusicServiceTests tests;

    @DataProvider
    Object[][] configurations() {
        return new Object[][]{
            {"config-01.xml"},
            {"config-02.xml"},
            {"config-03.xml"},
            {"config-04.xml"},
            {"config-05.xml"},
            {"config-06.xml"},
            {Configuration7.class},
            {Configuration8.class},
            {Configuration9.class},
            {Configuration10.class}
        };
    }

    private void runMethod(Object config, Consumer<MusicService> method) {
        ApplicationContext context;
        if (config instanceof String) {
            context = new ClassPathXmlApplicationContext(config.toString());
        } else if (config instanceof Class<?>) {
            context = new AnnotationConfigApplicationContext((Class<?>) config);
        } else {
            throw new RuntimeException("Invalid configuration argument: " + config);
        }
        MusicService service = context.getBean(MusicService.class);
        method.accept(service);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testRunMethod() {
        runMethod(Boolean.TRUE, tests::testSongVoting);
    }

    @Test(dataProvider = "configurations")
    public void testSongVoting(Object config) {
        runMethod(config, tests::testSongVoting);
    }

    @Test(dataProvider = "configurations")
    public void testGetMatchingArtistNames(Object config) {
        runMethod(config, tests::testMatchingArtistNames);
    }

    @Test(dataProvider = "configurations")
    public void testGetSongsForArtist(Object config) {
        runMethod(config, tests::testSongsForArtist);
    }

    @Test(dataProvider = "configurations")
    public void testMatchingSongNamesForArtist(Object config) {
        runMethod(config, tests::testMatchingSongNamesForArtist);
    }
}
```

---

## Chapter 4: Lifecycle

### chapter04/pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.apress</groupId>
        <artifactId>bsg6</artifactId>
        <version>1.0</version>
    </parent>
    <artifactId>chapter04</artifactId>
    <version>1.0</version>
    <dependencies>
        <dependency>
            <groupId>jakarta.annotation</groupId>
            <artifactId>jakarta.annotation-api</artifactId>
            <version>2.1.1</version>
        </dependency>
    </dependencies>
</project>
```

### chapter04/src/main/java/com/bsg6/chapter04/HasData.java
```java
package com.bsg6.chapter04;

import java.util.Objects;

abstract class HasData {
    String datum = "default";

    public String getDatum() { return datum; }
    public void setDatum(String datum) { this.datum = datum; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HasData)) return false;
        HasData hasData = (HasData) o;
        return Objects.equals(getDatum(), hasData.getDatum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDatum());
    }
}
```

### chapter04/src/test/resources/config-01.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean name="foo" class="com.bsg6.chapter04.FirstObject" scope="singleton"/>
    <bean name="bar" class="com.bsg6.chapter04.FirstObject" scope="prototype"/>
</beans>
```

### chapter04/src/test/java/com/bsg6/chapter04/TestLifecycle01.java
```java
package com.bsg6.chapter04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.UUID;
import static org.testng.Assert.*;

class FirstObject extends HasData {}

@ContextConfiguration(locations = "/config-01.xml")
public class TestLifecycle01 extends AbstractTestNGSpringContextTests {
    @Autowired ApplicationContext context;

    @DataProvider
    Object[][] getReferences() {
        return new Object[][]{
            {"foo", true},
            {"bar", false}
        };
    }

    @Test(dataProvider = "getReferences")
    public void testReferenceTypes(String name, boolean singleton) {
        HasData o1 = context.getBean(name, HasData.class);
        String defaultValue = o1.getDatum();
        o1.setDatum(UUID.randomUUID().toString());
        HasData o2 = context.getBean(name, HasData.class);

        if (singleton) {
            assertSame(o1, o2);
            assertEquals(o1, o2);
            assertNotEquals(defaultValue, o2.getDatum());
        } else {
            assertNotSame(o1, o2);
            assertNotEquals(o1, o2);
            assertEquals(defaultValue, o2.getDatum());
        }
    }
}
```

### chapter04/src/test/resources/config-02.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean name="foo" class="com.bsg6.chapter04.SecondObject">
        <constructor-arg name="initialValue" value="Initial Value"/>
    </bean>
</beans>
```

### chapter04/src/test/java/com/bsg6/chapter04/TestLifecycle02.java
```java
package com.bsg6.chapter04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

class SecondObject extends HasData {
    SecondObject(String initialValue) {
        setDatum(initialValue);
    }
}

@ContextConfiguration(locations = "/config-02.xml")
public class TestLifecycle02 extends AbstractTestNGSpringContextTests {
    @Autowired ApplicationContext context;

    @Test
    public void testConstructorArgs() {
        SecondObject o = context.getBean("foo", SecondObject.class);
        assertEquals(o.getDatum(), "Initial Value");
    }
}
```

### chapter04/src/test/resources/config-03.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean name="foo" class="com.bsg6.chapter04.ThirdObject"
          init-method="init" destroy-method="dispose" />
</beans>
```

### chapter04/src/test/java/com/bsg6/chapter04/TestLifecycle03.java
```java
package com.bsg6.chapter04;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

class ThirdObject extends HasData {
    static Object semaphore = null;
    public void init() { semaphore = new Object(); }
    public void dispose() { semaphore = null; }
}

@ContextConfiguration(locations = "/config-03.xml")
public class TestLifecycle03 {
    @Test
    public void testInitDestroyMethods() {
        ConfigurableApplicationContext context = 
            new ClassPathXmlApplicationContext("/config-03.xml");
        ThirdObject o1 = context.getBean(ThirdObject.class);
        assertNotNull(ThirdObject.semaphore);
        assertEquals(o1.getDatum(), "default");
        context.close();
        assertNull(ThirdObject.semaphore);
    }
}
```

### chapter04/src/test/resources/config-04.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean name="foo" class="com.bsg6.chapter04.FourthObject" />
</beans>
```

### chapter04/src/test/java/com/bsg6/chapter04/TestLifecycle04.java
```java
package com.bsg6.chapter04;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

class FourthObject extends HasData implements InitializingBean, DisposableBean {
    static Object semaphore = null;

    @Override
    public void afterPropertiesSet() throws Exception {
        semaphore = new Object();
    }

    @Override
    public void destroy() throws Exception {
        semaphore = null;
    }
}

public class TestLifecycle04 {
    @Test
    public void testLifecycleMethods() {
        ConfigurableApplicationContext context = 
            new ClassPathXmlApplicationContext("/config-04.xml");
        FourthObject o1 = context.getBean(FourthObject.class);
        assertNotNull(FourthObject.semaphore);
        assertEquals(o1.getDatum(), "default");
        context.close();
        assertNull(FourthObject.semaphore);
    }
}
```

### chapter04/src/test/resources/annotated.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">
    <context:component-scan base-package="com.bsg6.chapter04"/>
</beans>
```

### chapter04/src/test/java/com/bsg6/chapter04/TestLifecycle05.java
```java
package com.bsg6.chapter04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.UUID;
import static org.testng.Assert.*;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
class FifthObject extends HasData {}

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class SixthObject extends HasData {}

@ContextConfiguration(locations = "/annotated.xml")
public class TestLifecycle05 extends AbstractTestNGSpringContextTests {
    @Autowired ApplicationContext context;

    @DataProvider
    Object[] getReferences() {
        return new Object[]{
            {FifthObject.class, true},
            {SixthObject.class, false}
        };
    }

    @Test(dataProvider = "getReferences")
    public void testReferenceTypes(Class<HasData> clazz, boolean singleton) {
        HasData o1 = context.getBean(clazz);
        String defaultValue = o1.getDatum();
        o1.setDatum(UUID.randomUUID().toString());
        HasData o2 = context.getBean(clazz);

        if (singleton) {
            assertSame(o1, o2);
            assertEquals(o1, o2);
            assertNotEquals(defaultValue, o2.getDatum());
        } else {
            assertNotSame(o1, o2);
            assertNotEquals(o1, o2);
            assertEquals(defaultValue, o2.getDatum());
        }
    }
}
```

### chapter04/src/test/java/com/bsg6/chapter04/TestLifecycle06.java
```java
package com.bsg6.chapter04;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

@Component
class SeventhObject extends HasData {
    static Object semaphore = null;

    @PostConstruct
    public void initialize() throws Exception {
        semaphore = new Object();
    }

    @PreDestroy
    public void dispose() throws Exception {
        semaphore = null;
    }
}

public class TestLifecycle06 {
    @Test
    public void testInitDestroyMethods() {
        ConfigurableApplicationContext context = 
            new ClassPathXmlApplicationContext("/annotated-06.xml");
        SeventhObject o1 = context.getBean(SeventhObject.class);
        assertNotNull(SeventhObject.semaphore);
        assertEquals(o1.getDatum(), "default");
        context.close();
        assertNull(SeventhObject.semaphore);
    }
}
```

### chapter04/src/test/java/com/bsg6/chapter04/TestLifecycle07.java
```java
package com.bsg6.chapter04;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

@Component
class EighthObject extends HasData implements InitializingBean, DisposableBean {
    static Object semaphore = null;

    @Override
    public void afterPropertiesSet() throws Exception {
        semaphore = new Object();
    }

    @Override
    public void destroy() throws Exception {
        semaphore = null;
    }
}

public class TestLifecycle07 {
    @Test
    public void testLifecycleMethods() {
        ConfigurableApplicationContext context = 
            new ClassPathXmlApplicationContext("/annotated-07.xml");
        EighthObject o1 = context.getBean(EighthObject.class);
        assertNotNull(EighthObject.semaphore);
        assertEquals(o1.getDatum(), "default");
        context.close();
        assertNull(EighthObject.semaphore);
    }
}
```

### chapter04/src/test/java/com/bsg6/chapter04/TestLifecycle08.java
```java
package com.bsg6.chapter04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.UUID;
import static org.testng.Assert.*;

class NinthObject extends HasData {}

@Configuration
class Config08 {
    @Bean
    public NinthObject foo() { return new NinthObject(); }

    @Bean
    @Scope("prototype")
    public NinthObject bar() { return new NinthObject(); }
}

@ContextConfiguration(classes = Config08.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class TestLifecycle08 extends AbstractTestNGSpringContextTests {
    @Autowired ApplicationContext context;

    @DataProvider
    Object[][] getReferences() {
        return new Object[][]{
            {"foo", true},
            {"bar", false}
        };
    }

    @Test(dataProvider = "getReferences")
    public void testReferenceTypes(String reference, boolean singleton) {
        HasData o1 = context.getBean(reference, HasData.class);
        String defaultValue = o1.getDatum();
        o1.setDatum(UUID.randomUUID().toString());
        HasData o2 = context.getBean(reference, HasData.class);

        if (singleton) {
            assertSame(o1, o2);
            assertEquals(o1, o2);
            assertNotEquals(defaultValue, o2.getDatum());
        } else {
            assertNotSame(o1, o2);
            assertNotEquals(o1, o2);
            assertEquals(defaultValue, o2.getDatum());
        }
    }
}
```

---

## Chapter 5: Spring and Jakarta EE

### chapter05-api/pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.apress</groupId>
        <artifactId>bsg6</artifactId>
        <version>1.0</version>
    </parent>
    <artifactId>chapter05-api</artifactId>
    <version>1.0</version>
    <dependencies>
        <dependency>
            <groupId>${project.parent.groupId}</groupId>
            <artifactId>chapter03</artifactId>
            <version>${project.parent.version}</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
        </dependency>
        <dependency>
            <groupId>jakarta.servlet</groupId>
            <artifactId>jakarta.servlet-api</artifactId>
            <version>6.0.0</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>
</project>
```

### chapter05-api/src/main/java/com/bsg6/chapter05/VoteForSongServlet.java
```java
package com.bsg6.chapter05;

import com.bsg6.chapter03.MusicService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import java.io.IOException;

@WebServlet(urlPatterns = "/vote")
public class VoteForSongServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ApplicationContext context = (ApplicationContext) req
                .getServletContext()
                .getAttribute("context");
        MusicService service = context.getBean(MusicService.class);
        ObjectMapper mapper = new ObjectMapper();
        String artist = req.getParameter("artist");
        String song = req.getParameter("song");

        if (artist == null || song == null) {
            log("Missing data in request: requires artist and song parameters");
            resp.setStatus(500);
        } else {
            log("Voting for artist " + artist + ", song " + song);
            service.voteForSong(artist, song);
            resp.setStatus(200);
            resp.getWriter().println(
                mapper.writeValueAsString(service.getSong(artist, song))
            );
        }
    }
}
```

### chapter05-api/src/main/java/com/bsg6/chapter05/GetSongsForArtistServlet.java
```java
package com.bsg6.chapter05;

import com.bsg6.chapter03.MusicService;
import com.bsg6.chapter03.model.Song;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/songs")
public class GetSongsForArtistServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ApplicationContext context = (ApplicationContext) req
                .getServletContext()
                .getAttribute("context");
        MusicService service = context.getBean(MusicService.class);
        ObjectMapper mapper = new ObjectMapper();
        String artist = req.getParameter("artist");

        if (artist == null) {
            log("Missing data in request: requires artist parameter");
            resp.setStatus(500);
        } else {
            List<Song> data = service.getSongsForArtist(artist);
            resp.setStatus(200);
            resp.getWriter().println(
                mapper.writeValueAsString(data)
            );
        }
    }
}
```

### chapter05-anno/pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.apress</groupId>
        <artifactId>bsg6</artifactId>
        <version>1.0</version>
    </parent>
    <artifactId>chapter05-anno</artifactId>
    <version>1.0</version>
    <packaging>war</packaging>
    <dependencies>
        <dependency>
            <groupId>${project.parent.groupId}</groupId>
            <artifactId>chapter05-api</artifactId>
            <version>${project.parent.version}</version>
        </dependency>
        <dependency>
            <groupId>jakarta.servlet</groupId>
            <artifactId>jakarta.servlet-api</artifactId>
            <version>6.0.0</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
        </dependency>
        <dependency>
            <groupId>com.samskivert</groupId>
            <artifactId>jmustache</artifactId>
            <version>1.15</version>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.eclipse.jetty</groupId>
                <artifactId>jetty-maven-plugin</artifactId>
                <version>11.0.15</version>
            </plugin>
        </plugins>
    </build>
</project>
```

### chapter05-anno/src/main/java/com/bsg6/chapter05/FirstHelloServlet.java
```java
package com.bsg6.chapter05;

import com.samskivert.mustache.Mustache;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;

@WebServlet(urlPatterns = "/hello1")
public class FirstHelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try (var in = Objects.requireNonNull(this)
                .getClass()
                .getResourceAsStream("/hello.html")) {
            try (var reader = new InputStreamReader(in)) {
                var output = Mustache.compile().compile(reader)
                        .execute(Map.of("name", "world"));
                response.getWriter().println(output);
            }
        }
    }
}
```

### chapter05-anno/src/main/resources/hello.html
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Hello, {{name }}</title>
</head>
<body>
    <p> Hello, {{name }}! </p>
</body>
</html>
```

### chapter05-anno/src/main/java/com/bsg6/chapter05/AnnotationContextListener.java
```java
package com.bsg6.chapter05;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@WebListener
public class AnnotationContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ApplicationContext context = buildAnnotationContext();
        event.getServletContext().setAttribute("context", context);
    }

    private ApplicationContext buildAnnotationContext() {
        AnnotationConfigWebApplicationContext context = 
            new AnnotationConfigWebApplicationContext();
        context.scan("com.bsg6.chapter03.mem03");
        context.refresh();
        return context;
    }
}
```

### chapter05-xml/pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.apress</groupId>
        <artifactId>bsg6</artifactId>
        <version>1.0</version>
    </parent>
    <artifactId>chapter05-xml</artifactId>
    <version>1.0</version>
    <packaging>war</packaging>
    <dependencies>
        <dependency>
            <groupId>${project.parent.groupId}</groupId>
            <artifactId>chapter05-api</artifactId>
            <version>${project.parent.version}</version>
        </dependency>
        <dependency>
            <groupId>jakarta.servlet</groupId>
            <artifactId>jakarta.servlet-api</artifactId>
            <version>6.0.0</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.eclipse.jetty</groupId>
                <artifactId>jetty-maven-plugin</artifactId>
                <version>11.0.15</version>
            </plugin>
        </plugins>
    </build>
</project>
```

### chapter05-xml/src/main/java/com/bsg6/chapter05/XmlContextListener.java
```java
package com.bsg6.chapter05;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

@WebListener
public class XmlContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ApplicationContext context = buildXmlContext(event.getServletContext());
        event.getServletContext().setAttribute("context", context);
    }

    private ApplicationContext buildXmlContext(ServletContext sc) {
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setServletContext(sc);
        context.refresh();
        return context;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}
```

### chapter05-xml/src/main/webapp/WEB-INF/applicationContext.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">
    <context:component-scan base-package="com.bsg6.chapter03.mem03" />
</beans>
```

---

## Chapter 6: Spring Web

### chapter06/pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.apress</groupId>
        <artifactId>bsg6</artifactId>
        <version>1.0</version>
    </parent>
    <artifactId>chapter06</artifactId>
    <version>1.0</version>
    <packaging>war</packaging>
    <dependencies>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
        </dependency>
        <dependency>
            <groupId>${project.parent.groupId}</groupId>
            <artifactId>chapter03</artifactId>
            <version>${project.parent.version}</version>
        </dependency>
        <dependency>
            <groupId>jakarta.servlet</groupId>
            <artifactId>jakarta.servlet-api</artifactId>
            <version>6.0.0</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
        </dependency>
        <dependency>
            <groupId>com.samskivert</groupId>
            <artifactId>jmustache</artifactId>
            <version>1.15</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
        </dependency>
        <dependency>
            <groupId>org.hamcrest</groupId>
            <artifactId>hamcrest</artifactId>
            <version>2.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.eclipse.jetty</groupId>
                <artifactId>jetty-maven-plugin</artifactId>
                <version>11.0.15</version>
            </plugin>
        </plugins>
    </build>
</project>
```

### chapter06/src/main/java/com/bsg6/chapter06/GreetingController.java
```java
package com.bsg6.chapter06;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {
    @GetMapping(path = "/greeting", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> greeting() {
        return new ResponseEntity<>("Hello, World!", HttpStatus.OK);
    }
}
```

### chapter06/src/test/java/com/bsg6/chapter06/TestGreetingController.java
```java
package com.bsg6.chapter06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Test
@WebAppConfiguration
@ContextConfiguration(classes = GatewayAppWebConfig.class)
public class TestGreetingController extends AbstractTestNGSpringContextTests {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Test
    public void greetingTest() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.wac)
                .build();
        this.mockMvc.perform(get("/greeting")
                .accept(MediaType.ALL))
                .andExpect(status().isOk());
    }
}
```

### chapter06/src/main/java/com/bsg6/chapter06/GatewayAppWebConfig.java
```java
package com.bsg6.chapter06;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.bsg6.chapter06", "com.bsg6.chapter03.mem03"})
public class GatewayAppWebConfig implements WebMvcConfigurer {
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(mustacheViewResolver());
    }

    @Bean
    public ViewResolver mustacheViewResolver() {
        var viewResolver = new MustacheViewResolver();
        viewResolver.setPrefix("/WEB-INF/templates/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }
}
```

### chapter06/src/main/java/com/bsg6/chapter06/GatewayAppInitializer.java
```java
package com.bsg6.chapter06;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class GatewayAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{GatewayAppWebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
```

### chapter06/src/main/java/com/bsg6/chapter06/MustacheView.java
```java
package com.bsg6.chapter06;

import com.samskivert.mustache.Mustache.Compiler;
import com.samskivert.mustache.Template;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.view.AbstractTemplateView;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Locale;
import java.util.Map;

public class MustacheView extends AbstractTemplateView {
    private Compiler compiler;
    private String charset;

    public void setCompiler(Compiler compiler) { this.compiler = compiler; }
    public void setCharset(String charset) { this.charset = charset; }

    @Override
    public boolean checkResource(Locale locale) throws Exception {
        var resource = getApplicationContext().getResource(getUrl());
        return (resource != null && resource.exists());
    }

    @Override
    protected void renderMergedTemplateModel(Map<String, Object> model,
                                             HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
        var template = createTemplate(getApplicationContext().getResource(getUrl()));
        if (template != null) {
            template.execute(model, response.getWriter());
        }
    }

    private Template createTemplate(Resource resource) throws IOException {
        try (Reader reader = getReader(resource)) {
            return this.compiler.compile(reader);
        }
    }

    private Reader getReader(Resource resource) throws IOException {
        if (this.charset != null) {
            return new InputStreamReader(resource.getInputStream(), this.charset);
        }
        return new InputStreamReader(resource.getInputStream());
    }
}
```

### chapter06/src/main/java/com/bsg6/chapter06/MustacheViewResolver.java
```java
package com.bsg6.chapter06;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Mustache.Compiler;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

public class MustacheViewResolver extends AbstractTemplateViewResolver {
    private final Compiler compiler;
    private String charset;

    public MustacheViewResolver() {
        this.compiler = Mustache.compiler();
        setViewClass(requiredViewClass());
    }

    public MustacheViewResolver(Compiler compiler) {
        this.compiler = compiler;
        setViewClass(requiredViewClass());
    }

    protected Class<?> requiredViewClass() {
        return MustacheView.class;
    }

    public void setCharset(String charset) { this.charset = charset; }

    @Override
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        var view = (MustacheView) super.buildView(viewName);
        view.setCompiler(this.compiler);
        view.setCharset(this.charset);
        return view;
    }

    @Override
    protected AbstractUrlBasedView instantiateView() {
        if ((getViewClass() == MustacheView.class)) {
            return new MustacheView();
        } else {
            return super.instantiateView();
        }
    }
}
```

### chapter06/src/main/java/com/bsg6/chapter06/GetSongsController.java
```java
package com.bsg6.chapter06;

import com.bsg6.chapter03.MusicService;
import com.bsg6.chapter03.model.Song;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class GetSongsController {
    MusicService service;

    GetSongsController(MusicService service) {
        this.service = service;
    }

    @GetMapping("/songs")
    public ResponseEntity<List<Song>> getSongsByArtist(
            @RequestParam("artist") String artist) {
        var data = service.getSongsForArtist(artist);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/artists/{artist}/songs/{name}")
    public ResponseEntity<Song> getSong(
            @PathVariable("artist") final String artist,
            @PathVariable("name") final String name) {
        var artistDecoded = URLDecoder.decode(artist, StandardCharsets.UTF_8);
        var nameDecoded = URLDecoder.decode(name, StandardCharsets.UTF_8);
        var song = service.getSong(artistDecoded, nameDecoded);
        return new ResponseEntity<>(song, HttpStatus.OK);
    }
}
```

### chapter06/src/test/java/com/bsg6/chapter06/TestGetSongsController.java
```java
package com.bsg6.chapter06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Test
@WebAppConfiguration
@ContextConfiguration(classes = GatewayAppWebConfig.class)
public class TestGetSongsController extends AbstractTestNGSpringContextTests {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Test
    public void getSongControllerTest() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.wac)
                .build();
        MvcResult result = this.mockMvc.perform(get("/songs")
                .param("artist", "van halen")
                .param("name", "jump"))
                .andReturn();
        this.mockMvc.perform(get("/songs")
                .param("artist", "van halen")
                .param("name", "jump")
                .accept(MediaType.ALL))
                .andExpect(status().isOk());
    }

    @Test
    public void getSongsTestWithoutParameters() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.wac)
                .build();
        this.mockMvc.perform(get("/songs")
                .accept(MediaType.ALL))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getSongsByArtistTest() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.wac)
                .build();
        this.mockMvc.perform(get("/songs")
                .param("artist", "van halen")
                .accept(MediaType.ALL))
                .andExpect(status().isOk());
    }
}
```

### chapter06/src/main/java/com/bsg6/chapter06/GreetingWithModelController.java
```java
package com.bsg6.chapter06;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GreetingWithModelController {
    @GetMapping(name = "greeting", path = "/greeting/{name}")
    public String greeting(@PathVariable(name = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}
```

### chapter06/src/main/webapp/WEB-INF/templates/greeting.html
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Hello, {{name }}</title>
</head>
<body>
    <p> Hello, {{name }} </p>
</body>
</html>
```

### chapter06/src/main/java/com/bsg6/chapter06/ArtistNotFoundException.java
```java
package com.bsg6.chapter06;

public class ArtistNotFoundException extends RuntimeException {
    public ArtistNotFoundException(String message) {
        super(message);
    }
}
```

### chapter06/src/main/java/com/bsg6/chapter06/GetArtistsExceptionController.java
```java
package com.bsg6.chapter06;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GetArtistsExceptionController {
    MusicService service;

    GetArtistsExceptionController(MusicService service) {
        this.service = service;
    }

    @GetMapping("/artists/{artist}")
    @ResponseBody
    public ResponseEntity<String> getSong(@PathVariable String artist) {
        throw new ArtistNotFoundException("Artist with name " + artist + " not found");
    }

    @ExceptionHandler(ArtistNotFoundException.class)
    public ModelAndView handleCustomException(ArtistNotFoundException ex) {
        var model = new ModelAndView("error");
        model.addObject("message", ex.getMessage());
        model.addObject("statusCode", 404);
        return model;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllExceptions(Exception ex) {
        var model = new ModelAndView("error");
        model.addObject("message", ex.getMessage());
        model.addObject("statusCode", 500);
        return model;
    }
}
```

### chapter06/src/main/webapp/WEB-INF/templates/error.html
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Error {{ statusCode }}</title>
</head>
<body>
    <p> An error has occurred with status: {{ statusCode }} and message: {{ message }} </p>
</body>
</html>
```

---

## Chapter 7: Spring Boot

### chapter07/pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.apress</groupId>
        <artifactId>bsg6</artifactId>
        <version>1.0</version>
    </parent>
    <artifactId>chapter07</artifactId>
    <version>1.0</version>
    <packaging>war</packaging>
    <dependencies>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${springBootVersion}</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```

### chapter07/src/main/java/com/bsg6/chapter07/Chapter7Application.java
```java
package com.bsg6.chapter07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Chapter7Application {
    public static void main(String[] args) {
        SpringApplication.run(Chapter7Application.class, args);
    }
}
```

### chapter07/src/main/java/com/bsg6/chapter07/Greeting.java
```java
package com.bsg6.chapter07;

import java.util.Objects;

public class Greeting {
    String message;

    public Greeting(String message) {
        this.message = message;
    }

    public Greeting() {}

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Greeting)) return false;
        Greeting greeting = (Greeting) o;
        return Objects.equals(getMessage(), greeting.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessage());
    }
}
```

### chapter07/src/main/java/com/bsg6/chapter07/GreetingController.java
```java
package com.bsg6.chapter07;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    @RequestMapping(value = {"/greeting/{name}", "/greeting/"})
    Greeting greeting(@PathVariable(required = false) String name) {
        String object = name != null ? name : "world";
        if (object.equalsIgnoreCase("jack griffin")) {
            return new Greeting("I don't know who you are.");
        } else {
            return new Greeting("Hello, " + object + "!");
        }
    }
}
```

### chapter07/src/test/java/com/bsg6/chapter07/TestGreetingController.java
```java
package com.bsg6.chapter07;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestGreetingController extends AbstractTestNGSpringContextTests {
    @Autowired
    private GreetingController greetingController;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @DataProvider
    Object[][] greetingData() {
        return new Object[][]{
            new Object[]{null, "Hello, world!"},
            new Object[]{"World", "Hello, World!"},
            new Object[]{"Andrew", "Hello, Andrew!"},
            new Object[]{"Jack Griffin", "I don't know who you are."}
        };
    }

    @Test(dataProvider = "greetingData")
    public void testRestGreeting(String name, String greeting) {
        String url = "http://localhost:" + port + "/greeting/" + (name != null ? name : "");
        ResponseEntity<Greeting> result = restTemplate.getForEntity(url, Greeting.class);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody().getMessage(), greeting);
    }

    @Test(dataProvider = "greetingData")
    public void testDirectGreeting(String name, String greeting) {
        assertEquals(greetingController.greeting(name).getMessage(), greeting);
    }
}
```

### chapter07/src/main/resources/static/hello-boot.html
```html
<!DOCTYPE html>
<html>
<head>
    <title>Hello, World</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
    <script>
        function submitForm() {
            $.get('http://localhost:8080/greeting/' + 
                  $('#helloform input[name=name]').val(),
                  function(data) {
                      $("#greeting").text(data.message);
                  },
                  'json');
        }
    </script>
    <style>
        p#greeting {
            font-family: "Andale Mono",monospace;
        }
    </style>
</head>
<body>
    <div>
        <form id="helloform">
            <p>
                Hello, what is your name?
                <input type="text" name="name"/>
                <input type="submit" value="Submit" onclick="submitForm()"/>
            </p>
            <p id="greeting"></p>
        </form>
    </div>
</body>
</html>
```

### chapter07/src/main/resources/application.properties
```properties
spring.datasource.url=jdbc:h2:./chapter07;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
spring.sql.init.platform=h2
```

### chapter07/src/main/resources/schema.sql
```sql
DROP INDEX IF EXISTS artist_name;
DROP TABLE IF EXISTS artists;

CREATE TABLE IF NOT EXISTS ARTISTS
(
    id BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    name VARCHAR(64) NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS artist_name ON ARTISTS(name);
```

### chapter07/src/main/resources/data.sql
```sql
INSERT INTO ARTISTS (NAME) VALUES ('Threadbare Loaf');
INSERT INTO ARTISTS (NAME) VALUES ('Therapy Zeppelin');
INSERT INTO ARTISTS (NAME) VALUES ('Clancy In Silt');
```

### chapter07/src/main/java/com/bsg6/chapter07/Artist.java
```java
package com.bsg6.chapter07;

import java.util.Objects;
import java.util.StringJoiner;

public class Artist implements Comparable<Artist> {
    private int id;
    private String name;

    public Artist() {}

    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return new StringJoiner(", ", Artist.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;
        Artist artist = (Artist) o;
        return getId() == artist.getId() &&
                Objects.equals(getName().toLowerCase(), artist.getName().toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public int compareTo(Artist o) {
        return o.getName().toLowerCase().compareTo(getName().toLowerCase());
    }
}
```

### chapter07/src/main/java/com/bsg6/chapter07/ArtistNotFoundException.java
```java
package com.bsg6.chapter07;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArtistNotFoundException extends RuntimeException {
    public ArtistNotFoundException(String message) {
        super(message);
    }

    public ArtistNotFoundException(Exception e) {
        super(e);
    }
}
```

### chapter07/src/main/java/com/bsg6/chapter07/ArtistRepository.java
```java
package com.bsg6.chapter07;

import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArtistRepository {
    private DataSource dataSource;

    public ArtistRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Artist findArtistById(int id) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            return findArtistById(conn, id);
        }
    }

    private Artist findArtistById(Connection conn, int id) {
        String sql = "SELECT * FROM artists WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Artist(id, rs.getString("name"));
                } else {
                    throw new ArtistNotFoundException(id + " not found in artist database");
                }
            }
        } catch (SQLException e) {
            throw new ArtistNotFoundException(e);
        }
    }

    public Artist saveArtist(String name) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            try {
                return saveArtist(conn, name);
            } catch (SQLException e) {
                return findArtistByName(conn, name);
            }
        }
    }

    private Artist saveArtist(Connection conn, String name) throws SQLException {
        String sql = "INSERT INTO ARTISTS (NAME) VALUES (?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                int id = rs.getInt(1);
                return new Artist(id, name);
            }
        }
    }

    public Artist findArtistByName(String name) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            return findArtistByName(conn, name);
        }
    }

    private Artist findArtistByName(Connection conn, String name) throws SQLException {
        String sql = "SELECT * FROM artists WHERE LOWER(name)=LOWER(?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Artist(rs.getInt("id"), rs.getString("name"));
                } else {
                    throw new ArtistNotFoundException(name + " not found in artist database");
                }
            }
        }
    }

    public List<Artist> findAllArtistsByName(String name) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            return findAllArtistsByName(conn, name);
        }
    }

    private List<Artist> findAllArtistsByName(Connection conn, String name) throws SQLException {
        String sql = "SELECT * FROM artists WHERE LOWER(name) LIKE LOWER(?) ORDER BY name";
        List<Artist> artists = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    artists.add(new Artist(rs.getInt("id"), rs.getString("name")));
                }
            }
        }
        return artists;
    }
}
```

### chapter07/src/main/java/com/bsg6/chapter07/ArtistController.java
```java
package com.bsg6.chapter07;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
public class ArtistController {
    private ArtistRepository service;

    public ArtistController(ArtistRepository service) {
        this.service = service;
    }

    @GetMapping("/artist/{id}")
    Artist findArtistById(@PathVariable int id) throws SQLException {
        return service.findArtistById(id);
    }

    @GetMapping({"/artist/search/{name}", "/artist/search/"})
    Artist findArtistByName(@PathVariable(required = false) String name) throws SQLException {
        if (name != null) {
            return service.findArtistByName(name);
        } else {
            throw new IllegalArgumentException("No artist name submitted");
        }
    }

    @PostMapping("/artist")
    Artist saveArtist(@RequestBody Artist artist) throws SQLException {
        return service.saveArtist(artist.getName());
    }

    @GetMapping({"/artist/match/{name}", "/artist/match/"})
    List<Artist> findArtistByMatchingName(@PathVariable(required = false) String name) throws SQLException {
        return service.findAllArtistsByName(name != null ? name : "");
    }

    @ExceptionHandler(ArtistNotFoundException.class)
    ProblemDetail handleArtistNotFound(ArtistNotFoundException e) {
        ProblemDetail problemDetails = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        problemDetails.setTitle("Artist Not Found");
        return problemDetails;
    }
}
```

### chapter07/src/test/java/com/bsg6/chapter07/TestArtistController.java
```java
package com.bsg6.chapter07;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestArtistController extends AbstractTestNGSpringContextTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @DataProvider
    Object[][] artistData() {
        return new Object[][]{
            new Object[]{1, "Threadbare Loaf"},
            new Object[]{2, "Therapy Zeppelin"},
            new Object[]{3, "Clancy in Silt"},
            new Object[]{-1, null},
            new Object[]{-1, "Not A Band"}
        };
    }

    @Test(dataProvider = "artistData")
    public void testGetArtist(int id, String name) {
        String url = "/artist/" + id;
        ResponseEntity<Artist> response = restTemplate.getForEntity(url, Artist.class);
        if (id != -1) {
            assertEquals(response.getStatusCode(), HttpStatus.OK);
            Artist artist = response.getBody();
            Artist data = new Artist(id, name);
            assertEquals(artist, data);
        } else {
            assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

    @Test(dataProvider = "artistData")
    public void testSearchForArtist(int id, String name) {
        String url = "/artist/search/" + (name != null ? name : "");
        ResponseEntity<Artist> response = restTemplate.getForEntity(url, Artist.class);
        if (name != null) {
            if (id == -1) {
                assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
            } else {
                assertEquals(response.getStatusCode(), HttpStatus.OK);
                Artist artist = response.getBody();
                Artist data = new Artist(id, name);
                assertEquals(artist, data);
            }
        } else {
            assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Test
    public void testSaveExistingArtist() {
        String url = "/artist";
        Artist newArtist = restTemplate.getForObject(url + "/1", Artist.class);
        ResponseEntity<Artist> response = restTemplate.postForEntity(url, newArtist, Artist.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Artist artist = response.getBody();
        assertNotNull(artist);
        int id = artist.getId();
        assertEquals(id, newArtist.getId());
        assertEquals(artist.getName(), newArtist.getName());
        response = restTemplate.getForEntity(url + "/" + id, Artist.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Artist foundArtist = response.getBody();
        assertNotNull(foundArtist);
        assertEquals(artist, foundArtist);
    }

    @DataProvider
    public Object[][] artistSearches() {
        return new Object[][]{
            new Object[]{"", 3},
            new Object[]{"T", 2},
            new Object[]{"Th", 2},
            new Object[]{"Thr", 1},
            new Object[]{"C", 1},
            new Object[]{"Z", 0}
        };
    }

    @Test(dataProvider = "artistSearches")
    public void testSearches(String name, int count) {
        ParameterizedTypeReference<List<Artist>> type = new ParameterizedTypeReference<>() {};
        String url = "/artist/match/" + name;
        ResponseEntity<List<Artist>> response = restTemplate.exchange(url, HttpMethod.GET, null, type);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        List<Artist> artists = response.getBody();
        assertNotNull(artists);
        assertEquals(artists.size(), count);
    }

    @Test(dependsOnMethods = "testSearches")
    public void testSaveArtist() {
        String url = "/artist";
        Artist newArtist = new Artist(0, "The Broken Keyboards");
        ResponseEntity<Artist> response = restTemplate.postForEntity(url, newArtist, Artist.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Artist artist = response.getBody();
        assertNotNull(artist);
        int id = artist.getId();
        assertNotEquals(id, 0);
        assertEquals(artist.getName(), newArtist.getName());
        response = restTemplate.getForEntity(url + "/" + id, Artist.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Artist foundArtist = response.getBody();
        assertNotNull(foundArtist);
        assertEquals(artist, foundArtist);
    }
}
```

---

## Chapter 8: Spring Data Access with JdbcTemplate

### chapter08/pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.apress</groupId>
        <artifactId>bsg6</artifactId>
        <version>1.0</version>
    </parent>
    <artifactId>chapter08</artifactId>
    <version>1.0</version>
    <packaging>war</packaging>
    <dependencies>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${springBootVersion}</version>
            </plugin>
        </plugins>
    </build>
</project>
```

### chapter08/src/main/java/com/bsg6/chapter08/Artist.java
```java
package com.bsg6.chapter08;

import org.springframework.lang.NonNull;
import java.util.Objects;
import java.util.StringJoiner;

public class Artist {
    Integer id;
    @NonNull
    String name;

    public Artist() {}

    public Artist(@NonNull String name) {
        this.name = name;
    }

    public Artist(Integer id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    @NonNull
    public String getName() { return name; }
    public void setName(@NonNull String name) { this.name = name; }

    @Override
    public String toString() {
        return new StringJoiner(", ", Artist.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist artist)) return false;
        return Objects.equals(getId(), artist.getId()) && 
               Objects.equals(getName(), artist.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
```

### chapter08/src/main/java/com/bsg6/chapter08/Song.java
```java
package com.bsg6.chapter08;

import org.springframework.lang.NonNull;
import java.util.Objects;
import java.util.StringJoiner;

public class Song {
    Integer id;
    @NonNull
    Integer artistId;
    @NonNull
    String name;
    int votes;

    public Song() {}

    public Song(Integer id, @NonNull Integer artistId, @NonNull String name, int votes) {
        this.id = id;
        this.artistId = artistId;
        this.name = name;
        this.votes = votes;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    @NonNull
    public Integer getArtistId() { return artistId; }
    public void setArtistId(@NonNull Integer artistId) { this.artistId = artistId; }

    @NonNull
    public String getName() { return name; }
    public void setName(@NonNull String name) { this.name = name; }

    public int getVotes() { return votes; }
    public void setVotes(int votes) { this.votes = votes; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song song)) return false;
        return getVotes() == song.getVotes() &&
                Objects.equals(getId(), song.getId()) &&
                Objects.equals(getArtistId(), song.getArtistId()) &&
                Objects.equals(getName(), song.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getArtistId(), getName(), getVotes());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Song.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("votes=" + votes)
                .toString();
    }
}
```

### chapter08/src/test/resources/application.properties
```properties
spring.sql.init.platform=h2
```

### chapter08/src/main/resources/schema.sql
```sql
CREATE TABLE IF NOT EXISTS artists
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(64) NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS artist_name ON artists(name);

CREATE TABLE IF NOT EXISTS songs
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    artist_id INT,
    name VARCHAR(64) NOT NULL,
    votes INT DEFAULT 0,
    FOREIGN KEY (artist_id) REFERENCES artists (id) ON UPDATE CASCADE
);

CREATE UNIQUE INDEX IF NOT EXISTS song_artist ON SONGS (artist_id, name);
```

### chapter08/src/test/resources/data.sql
```sql
INSERT INTO ARTISTS (NAME) VALUES ('Threadbare Loaf');
INSERT INTO ARTISTS (NAME) VALUES ('Therapy Zeppelin');
INSERT INTO ARTISTS (NAME) VALUES ('Clancy In Silt');

INSERT INTO SONGS (ARTIST_ID, NAME, VOTES)
VALUES ((select id from artists where name like 'Thre%'), 'Someone Stole the Flour', 4);
INSERT INTO SONGS (ARTIST_ID, NAME, VOTES)
VALUES ((select id from artists where name like 'Thre%'), 'What Happened to Our First CD?', 17);
INSERT INTO SONGS (ARTIST_ID, NAME, VOTES)
VALUES ((select id from artists where name like 'The%'), 'Medium', 4);
INSERT INTO SONGS (ARTIST_ID, NAME, VOTES)
VALUES ((select id from artists where name like 'C%'), 'Igneous', 5);
```

### chapter08/src/main/java/com/bsg6/chapter08/JdbcConfiguration.java
```java
package com.bsg6.chapter08;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcConfiguration {
}
```

### chapter08/src/main/java/com/bsg6/chapter08/MusicRepository.java
```java
package com.bsg6.chapter08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class MusicRepository {
    JdbcTemplate jdbcTemplate;
    @Autowired SongRowMapper songRowMapper;

    MusicRepository(JdbcTemplate template) {
        jdbcTemplate = template;
    }

    @Transactional
    public Artist findArtistById(Integer id) {
        return jdbcTemplate.query(
            "SELECT id, name FROM artists WHERE id=?",
            new Object[]{id},
            (rs, rowNum) -> new Artist(rs.getInt("id"), rs.getString("name"))
        ).stream().findFirst().orElse(null);
    }

    @Transactional
    public Artist findArtistByName(String name) {
        return internalFindArtistByName(name, true);
    }

    @Transactional
    public Artist findArtistByNameNoUpdate(String name) {
        return internalFindArtistByName(name, false);
    }

    private Artist internalFindArtistByName(String name, boolean update) {
        String insertSQL = "INSERT into artists (name) values (?)";
        String selectSQL = "SELECT id, name FROM artists WHERE lower(name)=lower(?)";
        return jdbcTemplate.query(
            selectSQL,
            new Object[]{name},
            (rs, rowNum) -> new Artist(rs.getInt("id"), rs.getString("name"))
        ).stream().findFirst().orElseGet(() -> {
            if (update) {
                KeyHolder keyHolder = new GeneratedKeyHolder();
                jdbcTemplate.update(conn -> {
                    PreparedStatement ps = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, name);
                    return ps;
                }, keyHolder);
                return new Artist(keyHolder.getKey().intValue(), name);
            } else {
                return null;
            }
        });
    }

    @Transactional
    public List<Song> getSongsForArtist(String artistName) {
        String selectSQL = "SELECT id, artist_id, name, votes FROM songs WHERE artist_id=? order by votes desc, name asc";
        Artist artist = internalFindArtistByName(artistName, true);
        return jdbcTemplate.query(selectSQL, new Object[]{artist.getId()}, songRowMapper);
    }

    @Transactional
    public List<String> getMatchingSongNamesForArtist(String artistName, String prefix) {
        String selectSQL = "SELECT name FROM songs WHERE artist_id=? and lower(name) like lower(?) order by name asc";
        Artist artist = internalFindArtistByName(artistName, true);
        return jdbcTemplate.query(
            selectSQL,
            new Object[]{artist.getId(), prefix + "%"},
            (rs, rowNum) -> rs.getString("name")
        );
    }

    @Transactional
    public List<String> getMatchingArtistNames(String prefix) {
        String selectSQL = "SELECT name FROM artists WHERE lower(name) like lower(?) order by name asc";
        return jdbcTemplate.query(
            selectSQL,
            new Object[]{prefix + "%"},
            (rs, rowNum) -> rs.getString("name")
        );
    }

    @Transactional
    public Song getSong(String artistName, String name) {
        return internalGetSong(artistName, name);
    }

    private Song internalGetSong(String artistName, String name) {
        String selectSQL = "SELECT id, artist_id, name, votes FROM songs WHERE artist_id=? and lower(name) = lower(?)";
        String insertSQL = "INSERT INTO SONGS (artist_id, name, votes) values(?,?,?)";
        Artist artist = internalFindArtistByName(artistName, true);
        return jdbcTemplate.query(
            selectSQL,
            new Object[]{artist.getId(), name},
            songRowMapper
        ).stream().findFirst().orElseGet(() -> {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(conn -> {
                PreparedStatement ps = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, artist.getId());
                ps.setString(2, name);
                ps.setInt(3, 0);
                return ps;
            }, keyHolder);
            return new Song(keyHolder.getKey().intValue(), artist.getId(), name, 0);
        });
    }

    @Transactional
    public Song voteForSong(String artistName, String name) {
        String updateSQL = "UPDATE songs SET votes=? WHERE id=?";
        Song song = internalGetSong(artistName, name);
        song.setVotes(song.getVotes() + 1);
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(updateSQL);
            ps.setInt(1, song.getVotes());
            ps.setInt(2, song.getId());
            return ps;
        });
        return song;
    }
}
```

### chapter08/src/main/java/com/bsg6/chapter08/SongRowMapper.java
```java
package com.bsg6.chapter08;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SongRowMapper implements RowMapper<Song> {
    @Override
    public Song mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Song(
            rs.getInt("id"),
            rs.getInt("artist_id"),
            rs.getString("name"),
            rs.getInt("votes")
        );
    }
}
```

### chapter08/src/test/java/com/bsg6/chapter08/MusicRepositoryTest.java
```java
package com.bsg6.chapter08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.function.Consumer;
import static org.testng.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MusicRepositoryTest extends AbstractTestNGSpringContextTests {
    @Autowired MusicRepository musicRepository;
    @Autowired JdbcTemplate jdbcTemplate;

    private Object[][] model = new Object[][]{
        {"Threadbare Loaf", "Someone Stole the Flour", 4},
        {"Threadbare Loaf", "What Happened To Our First CD?", 17},
        {"Therapy Zeppelin", "Medium", 4},
        {"Clancy in Silt", "Igneous", 5}
    };

    void iterateOverModel(Consumer<Object[]> consumer) {
        for (Object[] data : model) {
            consumer.accept(data);
        }
    }

    void populateData() {
        iterateOverModel(data -> {
            for (int i = 0; i < (Integer) data[2]; i++) {
                musicRepository.voteForSong((String) data[0], (String) data[1]);
            }
        });
    }

    @BeforeMethod
    void clearDatabase() {
        jdbcTemplate.update("DELETE FROM songs");
        jdbcTemplate.update("DELETE FROM artists");
        populateData();
    }

    @Test
    void testSongVoting() {
        iterateOverModel(data ->
            assertEquals(
                musicRepository.getSong((String) data[0], (String) data[1]).getVotes(),
                ((Integer) data[2]).intValue()
            ));
    }

    @Test
    void testSongsForArtist() {
        List<Song> songs = musicRepository.getSongsForArtist("Threadbare Loaf");
        assertEquals(songs.size(), 2);
        assertEquals(songs.get(0).getName(), "What Happened To Our First CD?");
        assertEquals(songs.get(0).getVotes(), 17);
        assertEquals(songs.get(1).getName(), "Someone Stole the Flour");
        assertEquals(songs.get(1).getVotes(), 4);
    }

    @Test
    void testMatchingArtistNames() {
        List<String> names = musicRepository.getMatchingArtistNames("Th");
        assertEquals(names.size(), 2);
        assertEquals(names.get(0), "Therapy Zeppelin");
        assertEquals(names.get(1), "Threadbare Loaf");
    }

    @Test
    void testMatchingSongNamesForArtist() {
        List<String> names = musicRepository.getMatchingSongNamesForArtist("Threadbare Loaf", "W");
        assertEquals(names.size(), 1);
        assertEquals(names.get(0), "What Happened To Our First CD?");
    }
}
```

### chapter08/src/main/java/com/bsg6/chapter08/ArtistController.java
```java
package com.bsg6.chapter08;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;
import java.nio.charset.Charset;
import java.util.List;

@RestController
public class ArtistController {
    private MusicRepository service;

    ArtistController(MusicRepository service) {
        this.service = service;
    }

    String decode(Object data) {
        return UriUtils.decode(data.toString(), Charset.defaultCharset());
    }

    @GetMapping(value = "/artists/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Artist findArtistById(@PathVariable int id) {
        Artist artist = service.findArtistById(id);
        if (artist != null) {
            return artist;
        } else {
            throw new ArtistNotFoundException();
        }
    }

    @GetMapping(value = {"/artists/search/{name}", "/artists/search/"},
                produces = MediaType.APPLICATION_JSON_VALUE)
    Artist findArtistByName(@PathVariable(required = false) String name) {
        if (name != null) {
            Artist artist = service.findArtistByNameNoUpdate(decode(name));
            if (artist != null) {
                return artist;
            } else {
                throw new ArtistNotFoundException();
            }
        } else {
            throw new NoArtistNameSubmittedException();
        }
    }

    @PostMapping(value="/artists", produces = MediaType.APPLICATION_JSON_VALUE)
    Artist saveArtist(@RequestBody Artist artist) {
        return service.findArtistByName(artist.getName());
    }

    @GetMapping(value={"/artists/match/{name}", "/artists/match/"},
                produces = MediaType.APPLICATION_JSON_VALUE)
    List<String> findArtistByMatchingName(@PathVariable(required = false) String name) {
        return service.getMatchingArtistNames(name != null ? decode(name) : "");
    }
}
```

### chapter08/src/main/java/com/bsg6/chapter08/ArtistNotFoundException.java
```java
package com.bsg6.chapter08;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Artist not found")
public class ArtistNotFoundException extends RuntimeException {
}
```

### chapter08/src/main/java/com/bsg6/chapter08/NoArtistNameSubmittedException.java
```java
package com.bsg6.chapter08;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "No artist name submitted")
public class NoArtistNameSubmittedException extends RuntimeException {
}
```

### chapter08/src/test/java/com/bsg6/chapter08/ArtistControllerTest.java
```java
package com.bsg6.chapter08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.util.UriUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.nio.charset.Charset;
import java.util.List;
import static org.testng.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArtistControllerTest extends AbstractTestNGSpringContextTests {
    @Autowired private TestRestTemplate restTemplate;
    @Autowired JdbcTemplate jdbcTemplate;

    String encode(Object data) {
        return UriUtils.encode(data.toString(), Charset.defaultCharset());
    }

    List<Object[]> getArtists() {
        return jdbcTemplate.query(
            "SELECT id, name FROM artists",
            (rs, rowNum) -> new Object[]{rs.getInt("id"), rs.getString("name")}
        );
    }

    @DataProvider
    Object[][] artistData() {
        List<Object[]> artists = getArtists();
        artists.add(new Object[]{-1, null});
        artists.add(new Object[]{-1, "Not A Band"});
        return artists.toArray(new Object[0][]);
    }

    @Test(dataProvider = "artistData")
    public void testGetArtistById(int id, String name) {
        String url = "/artists/" + id;
        ResponseEntity<Artist> response = restTemplate.getForEntity(url, Artist.class);
        if (id != -1) {
            assertEquals(response.getStatusCode(), HttpStatus.OK);
            Artist artist = response.getBody();
            Artist data = new Artist(id, name);
            assertEquals(artist.getId(), data.getId());
            assertEquals(artist.getName().toLowerCase(), data.getName().toLowerCase());
        } else {
            assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

    @Test(dataProvider = "artistData")
    public void testSearchForArtist(int id, String name) {
        String url = "/artists/search/" + (name != null ? encode(name) : "");
        ResponseEntity<Artist> response = restTemplate.getForEntity(url, Artist.class);
        if (name != null) {
            if (id == -1) {
                assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
            } else {
                assertEquals(response.getStatusCode(), HttpStatus.OK);
                Artist artist = response.getBody();
                Artist data = new Artist(id, name);
                assertEquals(artist.getName().toLowerCase(), data.getName().toLowerCase());
            }
        } else {
            assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        }
    }

    @Test
    public void testSaveExistingArtist() {
        String url = "/artists";
        Artist newArtist = new Artist("Threadbare Loaf");
        ResponseEntity<Artist> response = restTemplate.postForEntity(url, newArtist, Artist.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Artist artist = response.getBody();
        assertNotNull(artist);
        int id = artist.getId();
        assertEquals(artist.getName(), newArtist.getName());
        response = restTemplate.postForEntity(url, newArtist, Artist.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), artist);
    }

    @DataProvider
    public Object[][] artistSearches() {
        return new Object[][]{
            new Object[]{"", 3},
            new Object[]{"T", 2},
            new Object[]{"Th", 2},
            new Object[]{"Thr", 1},
            new Object[]{"C", 1},
            new Object[]{"Z", 0}
        };
    }

    @Test(dataProvider = "artistSearches")
    public void testSearches(String name, int count) {
        ParameterizedTypeReference<List<Artist>> type = new ParameterizedTypeReference<>() {};
        String url = "/artists/match/" + encode(name);
        ResponseEntity<List<Artist>> response = restTemplate.exchange(url, HttpMethod.GET, null, type);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        List<Artist> artists = response.getBody();
        assertNotNull(artists);
        assertEquals(artists.size(), count);
    }

    @Test(dependsOnMethods = "testSearches")
    public void testSaveArtist() {
        String url = "/artists";
        Artist newArtist = new Artist("The Broken Keyboards");
        ResponseEntity<Artist> response = restTemplate.postForEntity(url, newArtist, Artist.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Artist artist = response.getBody();
        assertNotNull(artist);
        int id = artist.getId();
        assertNotEquals(id, 0);
        assertEquals(artist.getName(), newArtist.getName());
        response = restTemplate.getForEntity(url + "/search/" + newArtist.getName(), Artist.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Artist foundArtist = response.getBody();
        assertNotNull(foundArtist);
        assertEquals(artist, foundArtist);
    }
}
```

### chapter08/src/main/java/com/bsg6/chapter08/SongController.java
```java
package com.bsg6.chapter08;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;
import java.nio.charset.Charset;
import java.util.List;

@RestController
public class SongController {
    private MusicRepository service;

    SongController(MusicRepository service) {
        this.service = service;
    }

    String decode(Object data) {
        return UriUtils.decode(data.toString(), Charset.defaultCharset());
    }

    @GetMapping(value="/artists/{name}/vote/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    Song voteForSong(@PathVariable String name, @PathVariable String title) {
        return service.voteForSong(decode(name), decode(title));
    }

    @GetMapping(value="/artists/{name}/song/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    Song getSong(@PathVariable String name, @PathVariable String title) {
        return service.getSong(decode(name), decode(title));
    }

    @GetMapping(value="/artists/{name}/songs", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Song> getSongsForArtist(@PathVariable String name) {
        return service.getSongsForArtist(decode(name));
    }

    @GetMapping(value={"/artists/{name}/match/{title}", "/artists/{name}/match/"},
                produces = MediaType.APPLICATION_JSON_VALUE)
    List<String> findSongsForArtist(@PathVariable String name,
                                    @PathVariable(required = false) String title) {
        return service.getMatchingSongNamesForArtist(decode(name), title != null ? decode(title) : "");
    }
}
```

### chapter08/src/test/java/com/bsg6/chapter08/SongControllerTest.java
```java
package com.bsg6.chapter08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.util.UriUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.nio.charset.Charset;
import java.util.List;
import java.util.function.Consumer;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SongControllerTest extends AbstractTestNGSpringContextTests {
    @LocalServerPort private int port;
    @Autowired private TestRestTemplate restTemplate;
    @Autowired JdbcTemplate jdbcTemplate;

    private Object[][] model = new Object[][]{
        {"Threadbare Loaf", "Someone Stole the Flour", 4},
        {"Threadbare Loaf", "What Happened To Our First CD?", 17},
        {"Therapy Zeppelin", "Medium", 4},
        {"Clancy in Silt", "Igneous", 5}
    };

    @BeforeMethod
    void clearDatabase() {
        jdbcTemplate.update("DELETE FROM songs");
        jdbcTemplate.update("DELETE FROM artists");
        populateData();
    }

    void iterateOverModel(Consumer<Object[]> consumer) {
        for (Object[] data : model) {
            consumer.accept(data);
        }
    }

    String encode(Object data) {
        return UriUtils.encode(data.toString(), Charset.defaultCharset());
    }

    void populateData() {
        iterateOverModel(data -> {
            for (int i = 0; i < (Integer) data[2]; i++) {
                String url = "http://localhost:" + port + "/artists/" + encode(data[0]) + "/vote/" + encode(data[1]);
                ResponseEntity<Song> response = restTemplate.getForEntity(url, Song.class);
                assertEquals(response.getStatusCode(), HttpStatus.OK);
            }
        });
    }

    @Test
    void testSongVoting() {
        iterateOverModel(data -> {
            String url = "http://localhost:" + port + "/artists/" + encode(data[0]) + "/song/" + encode(data[1]);
            ResponseEntity<Song> response = restTemplate.getForEntity(url, Song.class);
            assertEquals(response.getStatusCode(), HttpStatus.OK);
            Song song = response.getBody();
            assertNotNull(song);
            assertEquals(song.getName(), data[1]);
            assertEquals(song.getVotes(), ((Integer) data[2]).intValue());
        });
    }

    @Test
    void testSongsForArtist() {
        ParameterizedTypeReference<List<Song>> type = new ParameterizedTypeReference<>() {};
        String url = "http://localhost:" + port + "/artists/" + encode("Threadbare Loaf") + "/songs";
        ResponseEntity<List<Song>> response = restTemplate.exchange(url, HttpMethod.GET, null, type);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        List<Song> songs = response.getBody();
        assertEquals(songs.size(), 2);
        assertEquals(songs.get(0).getName(), "What Happened To Our First CD?");
        assertEquals(songs.get(0).getVotes(), 17);
        assertEquals(songs.get(1).getName(), "Someone Stole the Flour");
        assertEquals(songs.get(1).getVotes(), 4);
    }

    @Test
    void testMatchingSongNamesForArtist() {
        ParameterizedTypeReference<List<String>> type = new ParameterizedTypeReference<>() {};
        String url = "http://localhost:" + port + "/artists/" + encode("Threadbare Loaf") + "/match/" + encode("W");
        ResponseEntity<List<String>> response = restTemplate.exchange(url, HttpMethod.GET, null, type);
        List<String> names = response.getBody();
        assertEquals(names.size(), 1);
        assertEquals(names.get(0), "What Happened To Our First CD?");
    }
}
```

---

## Chapter 9: Persistence with Spring and Spring Data

### chapter09-api/pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.apress</groupId>
        <artifactId>bsg6</artifactId>
        <version>1.0</version>
    </parent>
    <artifactId>chapter09-api</artifactId>
    <version>1.0</version>
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.data</groupId>
                <artifactId>spring-data-bom</artifactId>
                <version>${springDataBomVersion}</version>
                <scope>import</scope>
                <type>pom</type>
            </dependency>
        </dependencies>
    </dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.data</groupId>
            <artifactId>spring-data-commons</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${springBootVersion}</version>
            </plugin>
        </plugins>
    </build>
</project>
```

### chapter09-api/src/main/java/com/bsg6/chapter09/common/BaseEntity.java
```java
package com.bsg6.chapter09.common;

public interface BaseEntity<ID> {
    ID getId();
    void setId(ID id);
}
```

### chapter09-api/src/main/java/com/bsg6/chapter09/common/BaseArtist.java
```java
package com.bsg6.chapter09.common;

public interface BaseArtist<ID> extends BaseEntity<ID> {
    String getName();
    void setName(String name);
}
```

### chapter09-api/src/main/java/com/bsg6/chapter09/common/BaseSong.java
```java
package com.bsg6.chapter09.common;

public interface BaseSong<T extends BaseArtist<ID>, ID> extends BaseEntity<ID> {
    T getArtist();
    void setArtist(T artist);
    String getName();
    void setName(String name);
    int getVotes();
    void setVotes(int votes);
}
```

### chapter09-api/src/main/java/com/bsg6/chapter09/common/BaseArtistRepository.java
```java
package com.bsg6.chapter09.common;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface BaseArtistRepository<T extends BaseArtist<ID>, ID>
        extends CrudRepository<T, ID> {
    List<T> findAllByNameIsLikeIgnoreCaseOrderByName(String name);
    Optional<T> findByNameIgnoreCase(String name);
}
```

### chapter09-api/src/main/java/com/bsg6/chapter09/common/BaseSongRepository.java
```java
package com.bsg6.chapter09.common;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface BaseSongRepository<A extends BaseArtist<ID>, S extends BaseSong<A, ID>, ID>
        extends CrudRepository<S, ID> {
    Optional<S> findByArtistIdAndNameIgnoreCase(ID artistId, String name);
    List<S> findByArtistIdOrderByVotesDesc(ID artistId);
    List<S> findByArtistIdAndNameLikeIgnoreCaseOrderByNameDesc(ID artistId, String name);
}
```

### chapter09-api/src/main/java/com/bsg6/chapter09/common/WildcardConverter.java
```java
package com.bsg6.chapter09.common;

public class WildcardConverter {
    private final String append;

    public WildcardConverter(String append) {
        this.append = append;
    }

    public String convertToWildCard(String data) {
        return data + append;
    }
}
```

### chapter09-api/src/main/java/com/bsg6/chapter09/common/BaseMusicService.java
```java
package com.bsg6.chapter09.common;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseMusicService<A extends BaseArtist<ID>, S extends BaseSong<A, ID>, ID> {
    private final BaseArtistRepository<A, ID> artistRepository;
    private final BaseSongRepository<A, S, ID> songRepository;
    private final WildcardConverter converter;

    protected BaseMusicService(BaseArtistRepository<A, ID> artistRepository,
                               BaseSongRepository<A, S, ID> songRepository,
                               WildcardConverter converter) {
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
        this.converter = converter;
    }

    protected abstract A createArtist(String name);
    protected abstract S createSong(A artist, String name);

    @Transactional
    public void voteForSong(String artistName, String songTitle) {
        S song = getSong(artistName, songTitle);
        song.setVotes(song.getVotes() + 1);
        songRepository.save(song);
    }

    @Transactional
    public S getSong(String artistName, String songTitle) {
        A artist = getArtist(artistName);
        return songRepository.findByArtistIdAndNameIgnoreCase(artist.getId(), songTitle)
                .orElseGet(() -> {
                    S entity = createSong(artist, songTitle);
                    songRepository.save(entity);
                    return entity;
                });
    }

    @Transactional
    public A getArtist(String artistName) {
        return artistRepository.findByNameIgnoreCase(artistName)
                .orElseGet(() -> {
                    A entity = createArtist(artistName);
                    artistRepository.save(entity);
                    return entity;
                });
    }

    @Transactional
    public List<S> getSongsForArtist(String artistName) {
        A artist = getArtist(artistName);
        return songRepository.findByArtistIdOrderByVotesDesc(artist.getId());
    }

    @Transactional(readOnly = true)
    public List<String> getMatchingArtistNames(String artistName) {
        return artistRepository
                .findAllByNameIsLikeIgnoreCaseOrderByName(converter.convertToWildCard(artistName))
                .stream()
                .map(A::getName)
                .collect(Collectors.toList());
    }

    @Transactional
    public A getArtistById(ID id) {
        return artistRepository.findById(id).orElse(null);
    }

    @Transactional
    public S getSongById(ID id) {
        return songRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<String> getMatchingSongNamesForArtist(String artistName, String songTitle) {
        A artist = getArtist(artistName);
        return songRepository
                .findByArtistIdAndNameLikeIgnoreCaseOrderByNameDesc(
                        artist.getId(),
                        converter.convertToWildCard(songTitle))
                .stream()
                .map(S::getName)
                .collect(Collectors.toList());
    }
}
```

### chapter09-test/pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.apress</groupId>
        <artifactId>bsg6</artifactId>
        <version>1.0</version>
    </parent>
    <artifactId>chapter09-test</artifactId>
    <version>1.0</version>
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.data</groupId>
                <artifactId>spring-data-bom</artifactId>
                <version>${springDataBomVersion}</version>
                <scope>import</scope>
                <type>pom</type>
            </dependency>
        </dependencies>
    </dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.apress</groupId>
            <artifactId>chapter09-api</artifactId>
            <version>1.0</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
        </dependency>
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${springBootVersion}</version>
            </plugin>
        </plugins>
    </build>
</project>
```

### chapter09-test/src/main/java/com/bsg6/chapter09/test/BaseArtistRepositoryTests.java
```java
package com.bsg6.chapter09.test;

import com.bsg6.chapter09.common.BaseArtist;
import com.bsg6.chapter09.common.BaseArtistRepository;
import com.bsg6.chapter09.common.WildcardConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public abstract class BaseArtistRepositoryTests<A extends BaseArtist<ID>, ID>
        extends AbstractTestNGSpringContextTests {
    @Autowired BaseArtistRepository<A, ID> artistRepository;
    @Autowired WildcardConverter converter;

    protected abstract A createArtist(String name);

    @BeforeMethod
    public void clearDatabase() {
        artistRepository.deleteAll();
    }

    @Test
    public void testOperations() {
        assertEquals(artistRepository.count(), 0);
        var firstEntity = createArtist("Threadbare Loaf");
        var secondEntity = createArtist("Therapy Zeppelin");
        firstEntity = artistRepository.save(firstEntity);
        assertNotNull(firstEntity.getId());
        var artist = artistRepository.findById(firstEntity.getId());
        assertTrue(artist.isPresent());
        assertEquals(artist.get(), firstEntity);
        var query = artistRepository.findAllByNameIsLikeIgnoreCaseOrderByName(
            converter.convertToWildCard("th"));
        assertEquals(query.size(), 1L);
        assertEquals(query.get(0), firstEntity);
        artistRepository.save(secondEntity);
        query = artistRepository.findAllByNameIsLikeIgnoreCaseOrderByName(
            converter.convertToWildCard("th"));
        assertEquals(query.size(), 2);
    }
}
```

### chapter09-test/src/main/java/com/bsg6/chapter09/test/BaseSongRepositoryTests.java
```java
package com.bsg6.chapter09.test;

import com.bsg6.chapter09.common.BaseArtist;
import com.bsg6.chapter09.common.BaseArtistRepository;
import com.bsg6.chapter09.common.BaseSong;
import com.bsg6.chapter09.common.BaseSongRepository;
import com.bsg6.chapter09.common.WildcardConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Optional;
import static org.testng.Assert.assertEquals;

public abstract class BaseSongRepositoryTests<
        A extends BaseArtist<ID>,
        S extends BaseSong<A, ID>,
        ID> extends AbstractTestNGSpringContextTests {
    @Autowired BaseArtistRepository<A, ID> artistRepository;
    @Autowired BaseSongRepository<A, S, ID> songRepository;
    @Autowired WildcardConverter converter;

    protected abstract A createArtist(String name);
    protected abstract S createSong(A artist, String name);

    private Object[][] model = new Object[][]{
        {"Threadbare Loaf", "Someone Stole the Flour", 4},
        {"Threadbare Loaf", "What Happened To Our First CD?", 17},
        {"Therapy Zeppelin", "Mfbrbl Is Not A Word", 0},
        {"Therapy Zeppelin", "Medium", 4},
        {"Clancy in Silt", "Igneous", 5}
    };

    @BeforeMethod
    public void clearDatabase() {
        songRepository.deleteAll();
        artistRepository.deleteAll();
        buildModel();
    }

    private void buildModel() {
        for (Object[] data : model) {
            String artistName = (String) data[0];
            String songTitle = (String) data[1];
            Integer votes = (Integer) data[2];
            Optional<A> artistQuery = artistRepository.findByNameIgnoreCase(artistName);
            A artist = artistQuery.orElseGet(() -> {
                A entity = createArtist(artistName);
                artistRepository.save(entity);
                return entity;
            });
            Optional<S> songQuery = songRepository.findByArtistIdAndNameIgnoreCase(
                artist.getId(), songTitle);
            if (songQuery.isEmpty()) {
                S song = createSong(artist, songTitle);
                song.setVotes(votes);
                songRepository.save(song);
            }
        }
    }

    @Test
    public void testOperations() {
        A artist = artistRepository.findByNameIgnoreCase("therapy zeppelin").orElseThrow();
        List<S> songs = songRepository.findByArtistIdAndNameLikeIgnoreCaseOrderByNameDesc(
            artist.getId(), converter.convertToWildCard("m"));
        assertEquals(songs.size(), 2);
        songs = songRepository.findByArtistIdOrderByVotesDesc(artist.getId());
        assertEquals(songs.size(), 2);
        assertEquals(songs.get(0).getName(), "Medium");
        assertEquals(songs.get(0).getVotes(), 4);
        assertEquals(songs.get(1).getVotes(), 0);
    }
}
```

### chapter09-test/src/main/java/com/bsg6/chapter09/test/BaseMusicServiceTests.java
```java
package com.bsg6.chapter09.test;

import com.bsg6.chapter09.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.function.Consumer;
import static org.testng.Assert.*;

public abstract class BaseMusicServiceTests<
        A extends BaseArtist<ID>,
        S extends BaseSong<A, ID>,
        ID> extends AbstractTestNGSpringContextTests {
    @Autowired BaseMusicService<A, S, ID> musicService;
    @Autowired BaseArtistRepository<A, ID> artistRepository;
    @Autowired BaseSongRepository<A, S, ID> songRepository;

    private Object[][] model = new Object[][]{
        {"Threadbare Loaf", "Someone Stole the Flour", 4},
        {"Threadbare Loaf", "What Happened To Our First CD?", 17},
        {"Therapy Zeppelin", "Medium", 4},
        {"Clancy in Silt", "Igneous", 5}
    };

    @BeforeMethod
    public void clearDatabase() {
        songRepository.deleteAll();
        artistRepository.deleteAll();
        populateService();
    }

    protected abstract ID getNonexistentId();

    void iterateOverModel(Consumer<Object[]> consumer) {
        for (Object[] data : model) {
            consumer.accept(data);
        }
    }

    void populateService() {
        iterateOverModel(data -> {
            for (int i = 0; i < (Integer) data[2]; i++) {
                musicService.voteForSong(data[0].toString(), data[1].toString());
            }
        });
    }

    @Test
    void testSongVoting() {
        iterateOverModel(data ->
            assertEquals(
                musicService.getSong(data[0].toString(), data[1].toString()).getVotes(),
                ((Integer) data[2]).intValue()
            ));
    }

    @Test
    void testSongsForArtist() {
        List<S> songs = musicService.getSongsForArtist("Threadbare Loaf");
        assertEquals(songs.size(), 2);
        assertEquals(songs.get(0).getName(), "What Happened To Our First CD?");
        assertEquals(songs.get(0).getVotes(), 17);
        assertEquals(songs.get(1).getName(), "Someone Stole the Flour");
        assertEquals(songs.get(1).getVotes(), 4);
    }

    @Test
    void testMatchingArtistNames() {
        List<String> names = musicService.getMatchingArtistNames("Th");
        assertEquals(names.size(), 2);
        assertEquals(names.get(0), "Therapy Zeppelin");
        assertEquals(names.get(1), "Threadbare Loaf");
    }

    @Test
    void testFindArtistById() {
        A artist = musicService.getArtist("Threadbare Loaf");
        assertNotNull(artist);
        A searched = musicService.getArtistById(artist.getId());
        assertNotNull(searched);
        assertEquals(artist.getName(), searched.getName());
        searched = musicService.getArtistById(getNonexistentId());
        assertNull(searched);
    }

    @Test
    void testFindSongById() {
        S song = musicService.getSong("Therapy Zeppelin", "Medium");
        assertNotNull(song);
        S searched = musicService.getSongById(song.getId());
        assertNotNull(searched);
        assertEquals(song.getName(), searched.getName());
        searched = musicService.getSongById(getNonexistentId());
        assertNull(searched);
    }

    @Test
    void testMatchingSongNamesForArtist() {
        List<String> names = musicService.getMatchingSongNamesForArtist("Threadbare Loaf", "W");
        assertEquals(names.size(), 1);
        assertEquals(names.get(0), "What Happened To Our First CD?");
    }
}
```

### chapter09-jpa/pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.apress</groupId>
        <artifactId>bsg6</artifactId>
        <version>1.0</version>
    </parent>
    <artifactId>chapter09-jpa</artifactId>
    <version>1.0</version>
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.data</groupId>
                <artifactId>spring-data-bom</artifactId>
                <version>${springDataBomVersion}</version>
                <scope>import</scope>
                <type>pom</type>
            </dependency>
        </dependencies>
    </dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.apress</groupId>
            <artifactId>chapter09-api</artifactId>
            <version>1.0</version>
        </dependency>
        <dependency>
            <groupId>com.apress</groupId>
            <artifactId>chapter09-test</artifactId>
            <version>1.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${springBootVersion}</version>
            </plugin>
        </plugins>
    </build>
</project>
```

### chapter09-jpa/src/main/java/com/bsg6/chapter09/jpa/Artist.java
```java
package com.bsg6.chapter09.jpa;

import com.bsg6.chapter09.common.BaseArtist;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class Artist implements BaseArtist<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @NonNull
    String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artist", fetch = FetchType.EAGER)
    @OrderBy("votes DESC")
    @JsonIgnore
    List<Song> songs = new ArrayList<>();

    public Artist() {}

    public Artist(@NonNull String name) {
        this.name = name;
    }

    @Override
    public String getName() { return name; }
    @Override
    public void setName(String name) { this.name = name; }
    @Override
    public Integer getId() { return id; }
    @Override
    public void setId(Integer id) { this.id = id; }

    @Override
    public String toString() {
        return new StringJoiner(", ", Artist.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("songs=" + songs)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist artist)) return false;
        return Objects.equals(getId(), artist.getId()) &&
               Objects.equals(getName(), artist.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
```

### chapter09-jpa/src/main/java/com/bsg6/chapter09/jpa/Song.java
```java
package com.bsg6.chapter09.jpa;

import com.bsg6.chapter09.common.BaseSong;
import org.springframework.lang.NonNull;
import jakarta.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(indexes = {
    @Index(name = "artist_song", columnList = "artist_id,name", unique = true)
})
public class Song implements BaseSong<Artist, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @ManyToOne
    @NonNull
    Artist artist;

    @NonNull
    String name;

    int votes;

    public Song() {}

    public Song(@NonNull Artist artist, @NonNull String name) {
        this.artist = artist;
        this.name = name;
    }

    @Override
    public Integer getId() { return id; }
    @Override
    public void setId(Integer id) { this.id = id; }
    @Override
    public Artist getArtist() { return artist; }
    @Override
    public void setArtist(Artist artist) { this.artist = artist; }
    @Override
    public String getName() { return name; }
    @Override
    public void setName(String name) { this.name = name; }
    @Override
    public int getVotes() { return votes; }
    @Override
    public void setVotes(int votes) { this.votes = votes; }

    @Override
    public String toString() {
        return new StringJoiner(", ", Song.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("artist=" + artist)
                .add("votes=" + votes)
                .toString();
    }
}
```

### chapter09-jpa/src/main/java/com/bsg6/chapter09/jpa/ArtistRepository.java
```java
package com.bsg6.chapter09.jpa;

import com.bsg6.chapter09.common.BaseArtistRepository;

public interface ArtistRepository extends BaseArtistRepository<Artist, Integer> {
}
```

### chapter09-jpa/src/main/java/com/bsg6/chapter09/jpa/SongRepository.java
```java
package com.bsg6.chapter09.jpa;

import com.bsg6.chapter09.common.BaseSongRepository;

public interface SongRepository extends BaseSongRepository<Artist, Song, Integer> {
}
```

### chapter09-jpa/src/main/java/com/bsg6/chapter09/jpa/MusicService.java
```java
package com.bsg6.chapter09.jpa;

import com.bsg6.chapter09.common.BaseMusicService;
import com.bsg6.chapter09.common.WildcardConverter;

public class MusicService extends BaseMusicService<Artist, Song, Integer> {
    MusicService(ArtistRepository artistRepository,
                 SongRepository songRepository,
                 WildcardConverter converter) {
        super(artistRepository, songRepository, converter);
    }

    @Override
    protected Artist createArtist(String name) {
        return new Artist(name);
    }

    @Override
    protected Song createSong(Artist artist, String name) {
        return new Song(artist, name);
    }
}
```

### chapter09-jpa/src/main/java/com/bsg6/chapter09/jpa/JpaConfiguration.java
```java
package com.bsg6.chapter09.jpa;

import com.bsg6.chapter09.common.WildcardConverter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@EnableJpaRepositories
@EntityScan
public class JpaConfiguration {
    @Bean
    WildcardConverter converter() {
        return new WildcardConverter("%");
    }

    @Bean
    MusicService musicService(ArtistRepository artistRepository,
                              SongRepository songRepository,
                              WildcardConverter converter) {
        return new MusicService(artistRepository, songRepository, converter);
    }
}
```

### chapter09-jpa/src/main/resources/application.properties
```properties
spring.datasource.url=jdbc:h2:./chapter9jpa;DB_CLOSE_DELAY=-1;
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

### chapter09-jpa/src/test/java/com/bsg6/chapter09/jpa/ArtistRepositoryTests.java
```java
package com.bsg6.chapter09.jpa;

import com.bsg6.chapter09.test.BaseArtistRepositoryTests;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ArtistRepositoryTests extends BaseArtistRepositoryTests<Artist, Integer> {
    protected Artist createArtist(String name) {
        return new Artist(name);
    }
}
```

### chapter09-jpa/src/test/java/com/bsg6/chapter09/jpa/SongRepositoryTests.java
```java
package com.bsg6.chapter09.jpa;

import com.bsg6.chapter09.test.BaseSongRepositoryTests;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class SongRepositoryTests extends BaseSongRepositoryTests<Artist, Song, Integer> {
    @Override
    protected Artist createArtist(String name) {
        return new Artist(name);
    }

    @Override
    protected Song createSong(Artist artist, String name) {
        return new Song(artist, name);
    }
}
```

### chapter09-jpa/src/test/java/com/bsg6/chapter09/jpa/MusicServiceTests.java
```java
package com.bsg6.chapter09.jpa;

import com.bsg6.chapter09.test.BaseMusicServiceTests;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class MusicServiceTests extends BaseMusicServiceTests<Artist, Song, Integer> {
    @Override
    protected Integer getNonexistentId() {
        return 1928491;
    }
}
```

### chapter09-mongo/pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.apress</groupId>
        <artifactId>bsg6</artifactId>
        <version>1.0</version>
    </parent>
    <artifactId>chapter09-mongo</artifactId>
    <version>1.0</version>
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.data</groupId>
                <artifactId>spring-data-bom</artifactId>
                <version>${springDataBomVersion}</version>
                <scope>import</scope>
                <type>pom</type>
            </dependency>
        </dependencies>
    </dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.apress</groupId>
            <artifactId>chapter09-api</artifactId>
            <version>1.0</version>
        </dependency>
        <dependency>
            <groupId>com.apress</groupId>
            <artifactId>chapter09-test</artifactId>
            <version>1.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-mongodb</artifactId>
        </dependency>
        <dependency>
            <groupId>de.flapdoodle.embed</groupId>
            <artifactId>de.flapdoodle.embed.mongo.spring30x</artifactId>
            <version>4.7.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${springBootVersion}</version>
            </plugin>
        </plugins>
    </build>
</project>
```

### chapter09-mongo/src/main/java/com/bsg6/chapter09/mongodb/Artist.java
```java
package com.bsg6.chapter09.mongodb;

import com.bsg6.chapter09.common.BaseArtist;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;
import java.util.Objects;
import java.util.StringJoiner;

@Document
public class Artist implements BaseArtist<String> {
    @Id
    String id;

    @NonNull
    String name;

    public Artist() {}

    public Artist(@NonNull String name) {
        this.name = name;
    }

    @Override
    public String getId() { return id; }
    @Override
    public void setId(String id) { this.id = id; }
    @Override
    @NonNull
    public String getName() { return name; }
    @Override
    public void setName(@NonNull String name) { this.name = name; }

    @Override
    public String toString() {
        return new StringJoiner(", ", Artist.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("name='" + name + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist artist)) return false;
        return Objects.equals(getId(), artist.getId()) &&
               Objects.equals(getName(), artist.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
```

### chapter09-mongo/src/main/java/com/bsg6/chapter09/mongodb/Song.java
```java
package com.bsg6.chapter09.mongodb;

import com.bsg6.chapter09.common.BaseSong;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

@Document
@CompoundIndexes(@CompoundIndex(unique = true, def = "{'artist':1, 'name':1}"))
public class Song implements BaseSong<Artist, String> {
    @Id
    String id;

    @NonNull
    @DBRef
    Artist artist;

    @NonNull
    String name;

    int votes;

    public Song() {}

    public Song(@NonNull Artist artist, @NonNull String name) {
        this.artist = artist;
        this.name = name;
    }

    @Override
    public String getId() { return id; }
    @Override
    public void setId(String id) { this.id = id; }
    @Override
    @NonNull
    public Artist getArtist() { return artist; }
    @Override
    public void setArtist(@NonNull Artist artist) { this.artist = artist; }
    @Override
    @NonNull
    public String getName() { return name; }
    @Override
    public void setName(@NonNull String name) { this.name = name; }
    @Override
    public int getVotes() { return votes; }
    @Override
    public void setVotes(int votes) { this.votes = votes; }
}
```

### chapter09-mongo/src/main/java/com/bsg6/chapter09/mongodb/ArtistRepository.java
```java
package com.bsg6.chapter09.mongodb;

import com.bsg6.chapter09.common.BaseArtistRepository;

public interface ArtistRepository extends BaseArtistRepository<Artist, String> {
}
```

### chapter09-mongo/src/main/java/com/bsg6/chapter09/mongodb/SongRepository.java
```java
package com.bsg6.chapter09.mongodb;

import com.bsg6.chapter09.common.BaseSongRepository;

public interface SongRepository extends BaseSongRepository<Artist, Song, String> {
}
```

### chapter09-mongo/src/main/java/com/bsg6/chapter09/mongodb/MusicService.java
```java
package com.bsg6.chapter09.mongodb;

import com.bsg6.chapter09.common.BaseMusicService;
import com.bsg6.chapter09.common.WildcardConverter;
import org.springframework.stereotype.Component;

@Component
public class MusicService extends BaseMusicService<Artist, Song, String> {
    MusicService(ArtistRepository artistRepository,
                 SongRepository songRepository,
                 WildcardConverter converter) {
        super(artistRepository, songRepository, converter);
    }

    @Override
    protected Artist createArtist(String name) {
        return new Artist(name);
    }

    @Override
    protected Song createSong(Artist artist, String name) {
        return new Song(artist, name);
    }
}
```

### chapter09-mongo/src/main/java/com/bsg6/chapter09/mongodb/MongoConfiguration.java
```java
package com.bsg6.chapter09.mongodb;

import com.bsg6.chapter09.common.WildcardConverter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootConfiguration
@EnableMongoRepositories
@EntityScan
public class MongoConfiguration {
    @Bean
    WildcardConverter converter() {
        return new WildcardConverter("");
    }

    @Bean
    MusicService musicService(ArtistRepository artistRepository,
                              SongRepository songRepository,
                              WildcardConverter converter) {
        return new MusicService(artistRepository, songRepository, converter);
    }
}
```

### chapter09-mongo/src/test/java/com/bsg6/chapter09/mongodb/ArtistRepositoryTests.java
```java
package com.bsg6.chapter09.mongodb;

import com.bsg6.chapter09.test.BaseArtistRepositoryTests;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
public class ArtistRepositoryTests extends BaseArtistRepositoryTests<Artist, String> {
    protected Artist createArtist(String name) {
        return new Artist(name);
    }
}
```

### chapter09-mongo/src/test/java/com/bsg6/chapter09/mongodb/SongRepositoryTests.java
```java
package com.bsg6.chapter09.mongodb;

import com.bsg6.chapter09.test.BaseSongRepositoryTests;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
public class SongRepositoryTests extends BaseSongRepositoryTests<Artist, Song, String> {
    @Override
    protected Artist createArtist(String name) {
        return new Artist(name);
    }

    @Override
    protected Song createSong(Artist artist, String name) {
        return new Song(artist, name);
    }
}
```

### chapter09-mongo/src/test/java/com/bsg6/chapter09/mongodb/MusicServiceTests.java
```java
package com.bsg6.chapter09.mongodb;

import com.bsg6.chapter09.test.BaseMusicServiceTests;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import java.util.UUID;

@DataMongoTest
public class MusicServiceTests extends BaseMusicServiceTests<Artist, Song, String> {
    @Override
    protected String getNonexistentId() {
        return UUID.randomUUID().toString();
    }
}
```

---

## Chapter 10: Spring Security

### chapter10/pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.apress</groupId>
        <artifactId>bsg6</artifactId>
        <version>1.0</version>
    </parent>
    <artifactId>chapter10</artifactId>
    <version>1.0</version>
    <packaging>war</packaging>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.yaml</groupId>
                    <artifactId>snakeyaml</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-mustache</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${springBootVersion}</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```

### chapter10/src/main/java/com/bsg6/chapter10/GatewayApplication.java
```java
package com.bsg6.chapter10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
```

### chapter10/src/main/java/com/bsg6/chapter10/DashboardController.java
```java
package com.bsg6.chapter10;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping("/")
    public String dashboard() {
        return "dashboard";
    }
}
```

### chapter10/src/main/resources/application.properties
```properties
spring.mustache.suffix:.html
spring.security.user.name=user
spring.security.user.password=password123
```

### chapter10/src/main/resources/templates/dashboard.html
```html
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard Page</title>
</head>
<body>
    Hello Admin! Welcome to your dashboard!
    <a href="logout">Logout</a>
</body>
</html>
```

### chapter10/src/test/java/com/bsg6/chapter10/GatewayApplicationTest.java
```java
package com.bsg6.chapter10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = GatewayApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class GatewayApplicationTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeClass
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testDashboardRequiresLogin() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testDashboardAfterLogin() throws Exception {
        this.mockMvc.perform(get("/")
                .with(SecurityMockMvcRequestPostProcessors.user("user").password("password")))
                .andExpect(status().isOk());
    }
}
```

### chapter10-custom/pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.apress</groupId>
        <artifactId>bsg6</artifactId>
        <version>1.0</version>
    </parent>
    <artifactId>chapter10-custom</artifactId>
    <version>1.0</version>
    <packaging>war</packaging>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.yaml</groupId>
                    <artifactId>snakeyaml</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-mustache</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${springBootVersion}</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```

### chapter10-custom/src/main/java/com/bsg6/chapter10/GatewayCustomApplication.java
```java
package com.bsg6.chapter10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayCustomApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayCustomApplication.class, args);
    }
}
```

### chapter10-custom/src/main/java/com/bsg6/chapter10/GatewaySecurityConfig.java
```java
package com.bsg6.chapter10;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class GatewaySecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        PasswordEncoder encoder = passwordEncoder();

        UserDetails adminUser = User
                .withUsername("admin")
                .password(encoder.encode("admin123"))
                .roles("ADMIN", "USER")
                .build();

        UserDetails regularUser = User
                .withUsername("user")
                .password(encoder.encode("user123"))
                .roles("USER")
                .build();

        manager.createUser(adminUser);
        manager.createUser(regularUser);
        return manager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login").permitAll()
                .requestMatchers("/dashboard").hasRole("USER")
                .requestMatchers("/admin_dashboard").hasRole("ADMIN")
                .anyRequest().authenticated()
        ).formLogin(formLogin -> formLogin
                .loginPage("/login")
                .permitAll()
        );
        return http.build();
    }
}
```

### chapter10-custom/src/main/java/com/bsg6/chapter10/CsrfTokenControllerAdvice.java
```java
package com.bsg6.chapter10;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CsrfTokenControllerAdvice {
    @ModelAttribute("csrf")
    public CsrfToken csrfToken(CsrfToken token) {
        return token;
    }
}
```

### chapter10-custom/src/main/java/com/bsg6/chapter10/NonSecureController.java
```java
package com.bsg6.chapter10;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NonSecureController {
    @GetMapping("/login")
    public String login(Model model, @RequestParam(required = false) String error) {
        model.addAttribute("hasError", error != null);
        return "login";
    }
}
```

### chapter10-custom/src/main/java/com/bsg6/chapter10/SecureController.java
```java
package com.bsg6.chapter10;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecureController {
    @GetMapping(value={"/", "/home"})
    public String home() {
        return "home";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/admin_dashboard")
    public String admin_dashboard() {
        return "admin_dashboard";
    }
}
```

### chapter10-custom/src/main/resources/templates/login.html
```html
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    {{#hasError}}
    <p style='color:red'>Invalid username and password.</p>
    {{/hasError}}
    <form action="login" method="POST">
        <p>
            <label for="username">Username</label>
            <input type="text" id="username" name="username" />
        </p>
        <p>
            <label for="password">Password</label>
            <input type="password" id="password" name="password" />
        </p>
        <button type="submit">Login</button>
        <input type="hidden" name="{{csrf.parameterName}}" value="{{csrf.token}}" />
    </form>
</body>
</html>
```

### chapter10-custom/src/main/resources/templates/home.html
```html
<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
    <h1>Home Page</h1>
    <a href="dashboard">Dashboard</a> | <a href="admin_dashboard">Admin Dashboard</a>
</body>
</html>
```

### chapter10-custom/src/main/resources/templates/dashboard.html
```html
<!DOCTYPE html>
<html>
<head>
    <title>Regular User Dashboard Page</title>
</head>
<body>
    Hello User! Welcome to your dashboard!
    <form id="logout-form" action="/logout" method="post">
        <input type="hidden" name="{{csrf.parameterName}}" value="{{csrf.token}}" />
        <button type="submit">Logout</button>
    </form>
</body>
</html>
```

### chapter10-custom/src/main/resources/templates/admin_dashboard.html
```html
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard Page</title>
</head>
<body>
    Hello Admin! Welcome to your dashboard!
    <form id="logout-form" action="/logout" method="post">
        <input type="hidden" name="{{csrf.parameterName}}" value="{{csrf.token}}" />
        <button type="submit">Logout</button>
    </form>
</body>
</html>
```

### chapter10-custom/src/test/java/com/bsg6/chapter10/GatewayCustomApplicationTest.java
```java
package com.bsg6.chapter10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = GatewayCustomApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class GatewayCustomApplicationTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeClass
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testHomepageRequiresLogin() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithUserDetails()
    public void testDashboardAfterLoginAsUser() throws Exception {
        this.mockMvc.perform(get("/dashboard"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin")
    public void testDashboardAfterLoginAsAdmin() throws Exception {
        this.mockMvc.perform(get("/admin_dashboard"))
                .andExpect(status().isOk());
    }
}
```

---

## Chapter 11: Spring Batch and Modulith

### chapter11-batch/pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.apress</groupId>
        <artifactId>bsg6</artifactId>
        <version>1.0</version>
    </parent>
    <artifactId>chapter11-batch</artifactId>
    <version>1.0</version>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-batch</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${springBootVersion}</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```

### chapter11-batch/src/main/java/com/bsg6/chapter11/GatewayBatchApplication.java
```java
package com.bsg6.chapter11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayBatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayBatchApplication.class, args);
    }
}
```

### chapter11-batch/src/main/resources/application.properties
```properties
file.artists.input=artists.csv
file.songs.input=songs.csv
spring.datasource.url=jdbc:h2:./chapter-11-batch;DB_CLOSE_DELAY=-1;
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.batch.jdbc.initialize-schema=always
```

### chapter11-batch/src/main/java/com/bsg6/chapter11/Artist.java
```java
package com.bsg6.chapter11;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Artist {
    @Id
    public Integer id;
    private String name;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
```

### chapter11-batch/src/main/java/com/bsg6/chapter11/Song.java
```java
package com.bsg6.chapter11;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Song {
    @Id
    private Integer id;
    private Integer artistId;
    private String name;
    private int votes;
    @Transient
    private Artist artist;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getArtistId() { return artistId; }
    public void setArtistId(Integer artistId) { this.artistId = artistId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getVotes() { return votes; }
    public void setVotes(int votes) { this.votes = votes; }
    public Artist getArtist() { return artist; }
    public void setArtist(Artist artist) { this.artist = artist; }

    public Song() {}
    public Song(String name) { this.name = name; }
}
```

### chapter11-batch/src/main/java/com/bsg6/chapter11/GatewayConfiguration.java
```java
package com.bsg6.chapter11;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;

@Configuration
public class GatewayConfiguration {
    @Value("${file.artists.input}")
    private String artistsFileInput;
    @Value("${file.songs.input}")
    private String songsFileInput;

    @Bean
    public FlatFileItemReader<Artist> artistReader() {
        return new FlatFileItemReaderBuilder<Artist>()
                .name("artistsReader")
                .resource(new ClassPathResource(artistsFileInput))
                .delimited()
                .names(new String[]{"id", "name"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Artist>() {{
                    setTargetType(Artist.class);
                }})
                .build();
    }

    @Bean
    public FlatFileItemReader<Song> songReader() {
        return new FlatFileItemReaderBuilder<Song>()
                .name("songsReader")
                .resource(new ClassPathResource(songsFileInput))
                .delimited()
                .names(new String[]{"id", "name", "artistId"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Song>() {{
                    setTargetType(Song.class);
                }})
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<Artist> artistWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Artist>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO artist (id, name) VALUES (:id, :name)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<Song> songWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Song>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO song (id, artist_id, name, votes) VALUES (:id, :artistId, :name, 0)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public SongProcessor songProcessor() {
        return new SongProcessor();
    }

    @Bean
    public Job importJob(JobRepository jobRepository, Step step1, Step step2, JobNotificationListener listener) {
        return new JobBuilder("importJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(step1)
                .next(step2)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                      JdbcBatchItemWriter<Artist> writer) {
        return new StepBuilder("step1", jobRepository)
                .<Artist, Artist>chunk(5, transactionManager)
                .reader(artistReader())
                .writer(writer)
                .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                      JdbcBatchItemWriter<Song> songsWriter) {
        return new StepBuilder("step2", jobRepository)
                .<Song, Song>chunk(10, transactionManager)
                .reader(songReader())
                .processor(songProcessor())
                .writer(songsWriter)
                .build();
    }
}
```

### chapter11-batch/src/main/java/com/bsg6/chapter11/SongProcessor.java
```java
package com.bsg6.chapter11;

import org.springframework.batch.item.ItemProcessor;

public class SongProcessor implements ItemProcessor<Song, Song> {
    @Override
    public Song process(final Song song) {
        if (song.getArtistId() != null) {
            Artist artist = new Artist();
            artist.setId(song.getArtistId());
            song.setArtist(artist);
        }
        return song;
    }
}
```

### chapter11-batch/src/main/java/com/bsg6/chapter11/JobNotificationListener.java
```java
package com.bsg6.chapter11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobNotificationListener implements JobExecutionListener {
    private static final Logger log = LoggerFactory.getLogger(JobNotificationListener.class);
    private final JdbcTemplate jdbcTemplate;

    public JobNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("DONE: Time to verify the results");
            log.info("Let's look at our list of artists!");
            jdbcTemplate.query("SELECT name FROM artist",
                    (rs, row) -> rs.getString(1))
                    .forEach(artist -> log.info("Found <{}> in the database.", artist));
            log.info("Let's look at our list of songs!");
            jdbcTemplate.query("SELECT name FROM song",
                    (rs, row) -> rs.getString(1))
                    .forEach(song -> log.info("Found <{}> in the database.", song));
        }
    }
}
```

### chapter11-modulith/pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.apress</groupId>
        <artifactId>bsg6</artifactId>
        <version>1.0</version>
    </parent>
    <artifactId>chapter11-modulith</artifactId>
    <version>1.0</version>
    <dependencies>
        <dependency>
            <groupId>org.springframework.modulith</groupId>
            <artifactId>spring-modulith-starter-core</artifactId>
            <version>1.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.modulith</groupId>
            <artifactId>spring-modulith-starter-test</artifactId>
            <version>1.0.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${springBootVersion}</version>
            </plugin>
        </plugins>
    </build>
</project>
```

### chapter11-modulith/src/main/java/com/bsg6/chapter11/GatewayApplication.java
```java
package com.bsg6.chapter11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
```

### chapter11-modulith/src/main/java/com/bsg6/chapter11/artist/ArtistDTO.java
```java
package com.bsg6.chapter11.artist;

public record ArtistDTO(Integer id, String name) {
}
```

### chapter11-modulith/src/main/java/com/bsg6/chapter11/artist/ArtistService.java
```java
package com.bsg6.chapter11.artist;

import com.bsg6.chapter11.artist.internal.Artist;
import com.bsg6.chapter11.artist.internal.ArtistRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArtistService {
    private final ArtistRepository repository;

    public ArtistService(ArtistRepository repository) {
        this.repository = repository;
    }

    public List<Artist> findAll() {
        return repository.findAll();
    }

    public ArtistDTO getArtist(Integer id) {
        return repository.findById(id)
                .map(artist -> new ArtistDTO(artist.getId(), artist.getName()))
                .orElse(null);
    }
}
```

### chapter11-modulith/src/main/java/com/bsg6/chapter11/artist/internal/Artist.java
```java
package com.bsg6.chapter11.artist.internal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Artist {
    @Id
    public Integer id;
    private String name;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
```

### chapter11-modulith/src/main/java/com/bsg6/chapter11/artist/internal/ArtistRepository.java
```java
package com.bsg6.chapter11.artist.internal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
}
```

### chapter11-modulith/src/main/java/com/bsg6/chapter11/song/SongDTO.java
```java
package com.bsg6.chapter11.song;

import com.bsg6.chapter11.artist.ArtistDTO;

public record SongDTO(Integer id, String name, ArtistDTO artistDTO) {
}
```

### chapter11-modulith/src/main/java/com/bsg6/chapter11/song/SongService.java
```java
package com.bsg6.chapter11.song;

import com.bsg6.chapter11.artist.ArtistDTO;
import com.bsg6.chapter11.artist.ArtistService;
import com.bsg6.chapter11.song.internal.SongRepository;
import org.springframework.stereotype.Service;

@Service
public class SongService {
    private final SongRepository repository;
    private final ArtistService artistService;

    public SongService(SongRepository repository, ArtistService artistService) {
        this.repository = repository;
        this.artistService = artistService;
    }

    public SongDTO getSong(Integer id) {
        return repository.findById(id)
                .map(song -> {
                    ArtistDTO artistDTO = artistService.getArtist(song.getArtistId());
                    return artistDTO != null ? new SongDTO(id, song.getTitle(), artistDTO) : null;
                })
                .orElse(null);
    }
}
```

### chapter11-modulith/src/main/java/com/bsg6/chapter11/song/internal/Song.java
```java
package com.bsg6.chapter11.song.internal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Song {
    @Id
    private Integer id;
    private Integer artistId;
    private String title;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getArtistId() { return artistId; }
    public void setArtistId(Integer artistId) { this.artistId = artistId; }
    public String getTitle() { return title; }
    public void setTitle(String name) { this.title = name; }
}
```

### chapter11-modulith/src/main/java/com/bsg6/chapter11/song/internal/SongRepository.java
```java
package com.bsg6.chapter11.song.internal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Integer> {
}
```

### chapter11-modulith/src/test/java/com/bsg6/chapter11/GatewayApplicationTest.java
```java
package com.bsg6.chapter11;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest(classes = GatewayApplication.class)
public class GatewayApplicationTest extends AbstractTestNGSpringContextTests {
    @Test
    public void testModulesValid() {
        ApplicationModules.of(GatewayApplication.class).verify();
    }
}
```

### chapter11-modulith/src/test/java/com/bsg6/chapter11/DocumentationTests.java
```java
package com.bsg6.chapter11;

import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;
import org.testng.annotations.Test;

public class DocumentationTests {
    ApplicationModules modules = ApplicationModules.of(GatewayApplication.class);

    @Test
    void writeDocumentationSnippets() {
        new Documenter(modules)
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml();
    }
}
```

---

## Code Flow Explanation

### Chapter 2 Flow

1. **Interface Definition** (`Greeter.java`)
   - Defines `setPrintStream()` and `greet()` methods
   - Enables dependency injection of output stream

2. **Implementation** (`HelloWorldGreeter.java`)
   - Implements `Greeter` interface
   - Defaults to `System.out`
   - Prints "Hello, World!" to configured stream

3. **Test Without Spring** (`GreeterTest.java`)
   - Creates `ByteArrayOutputStream` to capture output
   - Injects custom `PrintStream`
   - Asserts "Hello, World!" is printed

4. **Spring XML Configuration** (`applicationContext.xml`)
   - Defines beans for `HelloWorldGreeter`, `PrintStream`, `ByteArrayOutputStream`
   - Uses `<property>` for setter injection
   - Uses `<constructor-arg>` for constructor injection

5. **Test With Spring** (`SpringGreeterTest.java`)
   - Loads `ApplicationContext` from XML
   - Retrieves beans by name and type
   - Asserts output matches expected

### Chapter 3 Flow

1. **Domain Model**
   - `Artist`: Has name and Map of `Song` objects
   - `Song`: Has name and vote count
   - Implements `Comparable` for sorting by votes

2. **Music Service Interface** (`MusicService.java`)
   - Defines CRUD-like operations for artists and songs
   - Returns ordered lists for autocomplete features

3. **Abstract Implementation** (`AbstractMusicService.java`)
   - In-memory storage using `HashMap`
   - Provides default normalization methods
   - Implements all `MusicService` methods

4. **Configuration Approaches**
   - **Annotation-based**: Uses `@Component`, `@Autowired`, `@Qualifier`
   - **XML-based**: Uses `<bean>`, `<property>`, `<constructor-arg>`
   - **Java Config**: Uses `@Configuration`, `@Bean`

5. **Dependency Injection Patterns**
   - Field injection with `@Autowired`
   - Setter injection with XML `<property>`
   - Constructor injection with `@Autowired` on constructor

6. **Testing** (`MusicServiceTests.java`)
   - Reusable test suite for all implementations
   - Tests voting, sorting, and matching functionality

### Chapter 4 Flow

1. **Scope Management**
   - `singleton`: Same instance per context
   - `prototype`: New instance per request

2. **Lifecycle Callbacks**
   - `init-method`/`destroy-method`: XML configuration
   - `InitializingBean`/`DisposableBean`: Interface-based
   - `@PostConstruct`/`@PreDestroy`: Annotation-based

### Chapter 5 Flow

1. **Common API Module**
   - Defines servlets that use Spring context
   - Servlets: `VoteForSongServlet`, `GetSongsForArtistServlet`

2. **Annotation-based Web App**
   - Uses `@WebListener` to create Spring context
   - Context stored in ServletContext attribute
   - Uses `AnnotationConfigWebApplicationContext`

3. **XML-based Web App**
   - Uses `XmlWebApplicationContext`
   - Configuration in `/WEB-INF/applicationContext.xml`

4. **Servlet Integration**
   - Servlets retrieve Spring context from ServletContext
   - Use `MusicService` bean from context

### Chapter 6 Flow

1. **Spring MVC Configuration**
   - `@EnableWebMvc` enables Spring MVC
   - `ComponentScan` finds controllers
   - Custom `MustacheViewResolver` for template rendering

2. **Controller Development**
   - `@Controller` marks handler classes
   - `@GetMapping` maps HTTP GET requests
   - `@PathVariable` extracts URL parameters
   - `@RequestParam` extracts query parameters

3. **Testing with MockMvc**
   - Simulates HTTP requests without container
   - Verifies status codes and content

### Chapter 7 Flow

1. **Spring Boot Application**
   - `@SpringBootApplication` enables auto-configuration
   - `main()` method launches embedded container

2. **REST Controller**
   - `@RestController` for REST endpoints
   - `@RequestMapping` with multiple paths
   - Optional `@PathVariable`

3. **Testing with Spring Boot**
   - `@SpringBootTest` loads full context
   - `TestRestTemplate` for HTTP calls
   - `@LocalServerPort` gets random test port

4. **Database with Spring Boot**
   - H2 embedded database
   - `schema.sql` and `data.sql` for initialization
   - `application.properties` for configuration

### Chapter 8 Flow

1. **JdbcTemplate Setup**
   - `spring-boot-starter-jdbc` includes template
   - `@Repository` marks data access classes

2. **CRUD Operations**
   - `JdbcTemplate.query()` for reads
   - `JdbcTemplate.update()` for writes
   - RowMapper lambdas for result mapping

3. **Transaction Management**
   - `@Transactional` for atomic operations
   - Propagation and isolation levels

4. **REST Controllers**
   - `ArtistController` and `SongController`
   - URI decoding for special characters
   - Proper HTTP status codes

### Chapter 9 Flow

1. **Spring Data Architecture**
   - Repository interfaces for data access
   - Query methods derived from method names
   - `CrudRepository` provides standard operations

2. **JPA Implementation**
   - `@Entity` for mapping to database
   - Relationships with `@OneToMany`, `@ManyToOne`
   - `@GeneratedValue` for primary keys

3. **MongoDB Implementation**
   - `@Document` for mapping to collections
   - `@DBRef` for references
   - Different primary key type (String)

4. **Common Base Classes**
   - Interfaces for entities and repositories
   - Abstract service with common logic
   - Reusable test suite

### Chapter 10 Flow

1. **Spring Security Auto-configuration**
   - Default login page
   - Generated password on startup
   - All endpoints secured by default

2. **Custom Security Configuration**
   - `SecurityFilterChain` bean
   - `UserDetailsService` for user management
   - Role-based authorization
   - Custom login page with CSRF token

3. **REST Security**
   - HTTP Basic authentication
   - CSRF disabled for REST
   - `TestRestTemplate` with credentials

4. **OAuth2 Integration**
   - GitHub OAuth2 client
   - `.env` file for credentials
   - OAuth2 auto-configuration

### Chapter 11 Flow

1. **Spring Batch**
   - `Job` with multiple `Step`s
   - `ItemReader` reads CSV files
   - `ItemProcessor` transforms data
   - `ItemWriter` persists to database
   - Chunk-based processing

2. **Spring Modulith**
   - Application modules as subpackages
   - Internal packages hide implementation
   - DTOs for cross-module communication
   - Verification tests ensure boundaries
   - Documentation generation

### Chapter 12 Flow (Next Steps)

This chapter is conceptual with no code. It covers:
- Spring Initializr for project generation
- Alternative JVM languages
- WebMVC.fn functional endpoints
- Reactive programming with WebFlux
- Message queues with Spring
- GraphQL integration
- Cloud deployment considerations

# Comprehensive Interview Guide: Beginning Spring 6

Based on the book "Beginning Spring 6" by Joseph B. Ottinger and Andrew Lombardi, here's a comprehensive guide organized by key concepts with interview Q&A.

---

## 1. Spring Framework Fundamentals

### Key Concepts

**Q: What is Spring Framework and why was it created?**

**A:** Spring is an application framework providing dependency injection features for the Java Virtual Machine.

**Historical Context:**
- Created by Rod Johnson and Juergen Hoeller
- Responded to J2EE/EJB complexity
- Book: "J2EE Development without EJB" (2004)

**Problems with EJB:**
```
Looking up an EJB required:
1. JNDI context creation
2. Home object lookup
3. Narrow with PortableRemoteObject
4. Create the EJB instance
5. Handle multiple exceptions
```

**Spring's Six Themes:**
1. Simplicity
2. Productivity
3. Object orientation
4. Business requirements
5. Empirical process
6. Testability

> "EJB represented complexity that applications didn't need."

---

**Q: What is Dependency Injection and why is it important?**

**A:** Dependency Injection (DI) is a design pattern where objects receive their dependencies from an external source rather than creating them internally.

**Traditional Approach (Bad):**
```java
public class Greeter {
    private HelloWorld generator = new HelloWorldImplementation();
    // Hard-coded dependency, difficult to test
}
```

**Dependency Injection (Good):**
```java
public class Greeter {
    private HelloWorld generator;
    
    public void setGenerator(HelloWorld generator) {
        this.generator = generator; // Injected from outside
    }
}
```

**Benefits:**
- Loose coupling
- Testability
- Flexibility
- Configuration externalization

---

## 2. Container and Bean Configuration

### Key Concepts

**Q: What is the Spring Container and what is a Spring Bean?**

**A:** 

**Container:**
- Manages Spring beans
- Creates and wires instances
- Primary interaction point: `ApplicationContext`
- Manages lifecycle of objects

**Spring Bean:**
- Any object managed by Spring container
- Created, wired, and destroyed by container
- Usually POJOs with minimal Spring dependencies

**Bean Configuration Approaches:**
| Approach | Example |
|----------|---------|
| XML | `<bean id="foo" class="com.Foo"/>` |
| Annotation | `@Component` |
| Java Config | `@Configuration` + `@Bean` |

---

**Q: Explain the different ways to configure Spring beans.**

**A:**

**1. XML Configuration (Traditional):**
```xml
<bean id="helloGreeter" class="com.HelloWorldGreeter">
    <property name="printStream" ref="printStream"/>
</bean>
```

**2. Annotation Configuration:**
```java
@Component
public class HelloWorldGreeter {
    @Autowired
    private PrintStream printStream;
}
```

**3. Java Configuration:**
```java
@Configuration
public class AppConfig {
    @Bean
    public HelloWorldGreeter greeter() {
        return new HelloWorldGreeter();
    }
}
```

---

**Q: What is the difference between @Component, @Service, @Repository, and @Controller?**

**A:**

| Annotation | Purpose | Typical Use |
|------------|---------|-------------|
| `@Component` | Generic stereotype | Any Spring-managed bean |
| `@Service` | Business logic | Service layer classes |
| `@Repository` | Data access | DAO/Repository classes |
| `@Controller` | Web layer | Spring MVC controllers |

**Common Characteristics:**
- All are specializations of `@Component`
- Enable component scanning
- Add semantic meaning
- Can trigger specific behavior (e.g., `@Repository` translates persistence exceptions)

---

**Q: What is the difference between field injection, setter injection, and constructor injection?**

**A:**

| Type | Example | Pros | Cons |
|------|---------|------|------|
| Field | `@Autowired private Foo foo;` | Simple | Hard to test, immutable not possible |
| Setter | `@Autowired void setFoo(Foo f)` | Flexible | Mutable, can be called multiple times |
| Constructor | `public class Foo(@Autowired Bar b)` | Immutable, testable | Verbose, circular dependencies |

**Recommendation:** Use constructor injection for required dependencies.

---

**Q: What is @Qualifier and when would you use it?**

**A:** `@Qualifier` resolves ambiguity when multiple beans of the same type exist.

**Example:**
```java
@Component("foo")
public class SimpleNormalizer implements Normalizer {}

@Component("bar")
public class CapLeadingNormalizer implements Normalizer {}

// Usage
@Autowired
@Qualifier("foo")
private Normalizer normalizer;
```

---

## 3. Lifecycle Management

### Key Concepts

**Q: What are the bean scopes in Spring?**

**A:**

| Scope | Description |
|-------|-------------|
| `singleton` | One instance per container (default) |
| `prototype` | New instance on each request |
| `request` | One instance per HTTP request (web) |
| `session` | One instance per HTTP session (web) |
| `application` | One instance per ServletContext |

**Singleton vs Prototype:**
```java
@Scope("singleton")
@Component
class SingletonBean {}

@Scope("prototype")
@Component
class PrototypeBean {}
```

---

**Q: How do you manage bean lifecycle in Spring?**

**A:**

**1. XML Configuration:**
```xml
<bean init-method="init" destroy-method="cleanup"/>
```

**2. Interfaces:**
```java
public class MyBean implements InitializingBean, DisposableBean {
    @Override
    public void afterPropertiesSet() { /* init */ }
    @Override
    public void destroy() { /* cleanup */ }
}
```

**3. Annotations:**
```java
@Component
public class MyBean {
    @PostConstruct
    public void init() { /* initialization */ }
    
    @PreDestroy
    public void cleanup() { /* cleanup */ }
}
```

---

## 4. Spring Web MVC

### Key Concepts

**Q: What is Spring MVC and how does it work?**

**A:** Spring MVC is a Model-View-Controller framework for building web applications.

**Architecture:**
```
┌─────────────────────────────────────────────┐
│           DispatcherServlet                  │
│  (Front Controller - routes requests)       │
├─────────────────────────────────────────────┤
│                                              │
│  Controller  ←→  Model  ←→  View           │
│  (Handles      (Data)     (Renders          │
│   requests)                response)         │
└─────────────────────────────────────────────┘
```

**Key Components:**
1. `DispatcherServlet` - Entry point
2. `@Controller` - Handles requests
3. `Model` - Holds data
4. `ViewResolver` - Finds views

---

**Q: What is the difference between @Controller and @RestController?**

**A:**

| Aspect | @Controller | @RestController |
|--------|-------------|-----------------|
| Purpose | Traditional MVC | REST APIs |
| Response | View name | Object (JSON/XML) |
| @ResponseBody | Required | Implicit |
| Use Case | HTML pages | JSON/XML endpoints |

---

**Q: Explain the REST concepts and HTTP methods.**

**A:**

**REST Principles:**
- Base URI for resources
- HTTP methods for actions
- Stateless communication
- Resource representations

**HTTP Methods:**
| Method | Purpose | Idempotent |
|--------|---------|------------|
| GET | Retrieve resource | Yes |
| POST | Create resource | No |
| PUT | Update/replace resource | Yes |
| DELETE | Remove resource | Yes |
| PATCH | Partial update | No |

**Method Mappings:**
```java
@GetMapping("/resources")        // GET
@PostMapping("/resources")       // POST
@PutMapping("/resources/{id}")   // PUT
@DeleteMapping("/resources/{id}")// DELETE
@PatchMapping("/resources/{id}") // PATCH
```

---

## 5. Spring Boot

### Key Concepts

**Q: What is Spring Boot and why is it useful?**

**A:** Spring Boot is a project that simplifies Spring application development.

**Features:**
1. Auto-configuration
2. Embedded containers (Tomcat, Jetty)
3. Starter dependencies
4. Production-ready features (metrics, health)
5. No XML configuration required

**Quick Start:**
```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

---

**Q: What does @SpringBootApplication do?**

**A:** `@SpringBootApplication` combines three annotations:

```java
@SpringBootApplication = 
    @Configuration 
    + @EnableAutoConfiguration 
    + @ComponentScan
```

1. **@Configuration** - Marks as configuration class
2. **@EnableAutoConfiguration** - Enables auto-configuration
3. **@ComponentScan** - Scans for components in package

---

**Q: How do you test Spring Boot applications?**

**A:**

**1. Unit Test with @SpringBootTest:**
```java
@SpringBootTest
public class MyTest {
    @Autowired
    private TestRestTemplate restTemplate;
}
```

**2. Web Layer Test:**
```java
@WebMvcTest
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;
}
```

**3. Data Layer Test:**
```java
@DataJpaTest
public class RepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
}
```

---

## 6. Data Access with Spring

### Key Concepts

**Q: What is JdbcTemplate and why use it?**

**A:** JdbcTemplate is Spring's JDBC abstraction that simplifies database operations.

**Benefits:**
- Automatic resource management
- Exception translation
- Less boilerplate code
- Support for RowMapper

**Example:**
```java
@Repository
public class MusicRepository {
    private JdbcTemplate jdbcTemplate;
    
    public List<Artist> findArtists(String prefix) {
        return jdbcTemplate.query(
            "SELECT id, name FROM artists WHERE name LIKE ?",
            new Object[]{prefix + "%"},
            (rs, rowNum) -> new Artist(rs.getInt("id"), rs.getString("name"))
        );
    }
}
```

---

**Q: What is @Transactional and how does it work?**

**A:** `@Transactional` manages transaction boundaries declaratively.

**Propagation Levels:**
| Level | Description |
|-------|-------------|
| REQUIRED | Join existing or create new |
| REQUIRES_NEW | Suspend existing, create new |
| SUPPORTS | Join existing if available |
| MANDATORY | Must have existing transaction |
| NEVER | Cannot run in transaction |

**Isolation Levels:**
| Level | Description |
|-------|-------------|
| READ_UNCOMMITTED | Dirty reads allowed |
| READ_COMMITTED | No dirty reads |
| REPEATABLE_READ | Consistent reads |
| SERIALIZABLE | Full isolation |

---

**Q: What is Spring Data and how does it simplify data access?**

**A:** Spring Data provides a consistent programming model for data access.

**Key Features:**
1. Repository interfaces
2. Query method derivation
3. Code generation at runtime
4. Multiple database support

**Query Method Derivation:**
```java
public interface ArtistRepository extends CrudRepository<Artist, Integer> {
    // Derives query from method name
    List<Artist> findByNameIgnoreCase(String name);
    
    // Complex queries
    List<Artist> findAllByNameIsLikeIgnoreCaseOrderByName(String name);
}
```

**Supported Keywords:**
- `And` / `Or`
- `Between`
- `LessThan` / `GreaterThan`
- `Like`
- `OrderBy`

---

**Q: Compare JPA and MongoDB with Spring Data.**

**A:**

| Aspect | Spring Data JPA | Spring Data MongoDB |
|--------|-----------------|---------------------|
| Database | Relational (SQL) | Document (NoSQL) |
| Entity Annotation | `@Entity` | `@Document` |
| Primary Key | `@Id @GeneratedValue` | `@Id` (String) |
| Relationships | `@OneToMany`, `@ManyToOne` | `@DBRef` |
| Query Language | JPQL/SQL | MongoDB Query |
| Schema | Fixed | Flexible |

---

## 7. Spring Security

### Key Concepts

**Q: What is Spring Security and what does it provide?**

**A:** Spring Security provides authentication and authorization for Spring applications.

**Core Modules:**
| Module | Purpose |
|--------|---------|
| `spring-security-core` | Core authentication/authorization |
| `spring-security-config` | Configuration support |
| `spring-security-web` | Web security |
| `spring-security-test` | Testing support |

**Authentication vs Authorization:**
- **Authentication**: Who are you? (Login)
- **Authorization**: What can you do? (Permissions)

---

**Q: How do you configure Spring Security?**

**A:**

**1. Basic Configuration:**
```java
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/login").permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        ).formLogin(formLogin -> formLogin
            .loginPage("/login").permitAll()
        );
        return http.build();
    }
}
```

**2. In-Memory Users:**
```java
@Bean
public UserDetailsService userDetailsService() {
    return new InMemoryUserDetailsManager(
        User.withUsername("user")
            .password(encoder.encode("password"))
            .roles("USER")
            .build(),
        User.withUsername("admin")
            .password(encoder.encode("admin"))
            .roles("ADMIN", "USER")
            .build()
    );
}
```

---

**Q: What is CSRF protection and when should you disable it?**

**A:** CSRF (Cross-Site Request Forgery) protection prevents malicious websites from making requests on behalf of authenticated users.

**When to disable:** REST APIs that use tokens/JWT
```java
.httpBasic(withDefaults())
.csrf(AbstractHttpConfigurer::disable)
```

**When to keep:** Traditional web applications with session-based authentication

---

**Q: Explain OAuth2 integration with Spring Security.**

**A:** Spring Security provides OAuth2 client support for social login.

**Configuration:**
```properties
spring.security.oauth2.client.registration.github.client-id=${CLIENT_ID}
spring.security.oauth2.client.registration.github.client-secret=${CLIENT_SECRET}
```

**Flow:**
1. User clicks "Login with GitHub"
2. Redirected to GitHub authorization
3. GitHub redirects back with code
4. Spring exchanges code for access token
5. User authenticated

---

## 8. Spring Batch

### Key Concepts

**Q: What is Spring Batch and when would you use it?**

**A:** Spring Batch is a framework for batch processing - processing large volumes of data.

**Use Cases:**
- Data import/export
- Report generation
- ETL (Extract, Transform, Load)
- Scheduled jobs

**Key Components:**
```
Job
├── Step (chunk-based)
│   ├── ItemReader
│   ├── ItemProcessor (optional)
│   └── ItemWriter
└── Step (tasklet-based)
```

---

**Q: Explain chunk-based processing in Spring Batch.**

**A:** Chunk-based processing reads data in batches (chunks) and processes them together.

**Flow:**
```
Read  →  Process  →  Write
 (chunk)  (transform)  (persist)
```

**Example:**
```java
@Bean
public Step step(JobRepository jobRepository) {
    return new StepBuilder("step1", jobRepository)
        .<Artist, Artist>chunk(5, transactionManager)
        .reader(reader())
        .processor(processor())
        .writer(writer())
        .build();
}
```

---

## 9. Spring Modulith

### Key Concepts

**Q: What is Spring Modulith?**

**A:** Spring Modulith helps build well-structured applications based on module-driven domain design.

**Features:**
1. Verification of modular arrangements
2. Event publication and consumption
3. Integration testing individual modules
4. Documentation generation

**Module Structure:**
```
com.bsg6.chapter11.artist/      ← Application Module
├── ArtistService.java          ← API (public)
├── ArtistDTO.java              ← API
└── internal/                   ← Internal (private)
    ├── Artist.java
    └── ArtistRepository.java
```

---

**Q: How does Spring Modulith enforce module boundaries?**

**A:**

**1. Package Rules:**
- Subpackages of application modules are internal
- Only direct subpackages are considered modules
- Cross-module dependencies are validated

**2. Verification Test:**
```java
@Test
public void testModulesValid() {
    ApplicationModules.of(Application.class).verify();
}
```

**3. Automatic Checks:**
- No cycles in dependency graph
- No access to internal packages
- Respects defined module dependencies

---

## 10. Advanced Spring Concepts

### Key Concepts

**Q: What is Spring WebMVC.fn?**

**A:** Functional programming alternative to annotation-based controllers.

**Example:**
```java
@Bean
RouterFunction<ServerResponse> routes(PersonHandler ph) {
    return route()
        .GET("/people", ph::handleGetAllPeople)
        .GET("/people/{id}", ph::handleGetPersonById)
        .POST("/people", ph::handlePostPerson)
        .filter((req, next) -> {
            // Logging
            return next.handle(req);
        })
        .build();
}
```

**Benefits:**
- Centralized endpoint configuration
- Immutability
- Domain-specific language

---

**Q: What is Spring Reactive (WebFlux)?**

**A:** Spring WebFlux is a reactive programming model for asynchronous, non-blocking web applications.

**Key Concepts:**
- **Mono<T>**: 0 or 1 element
- **Flux<T>**: 0 to N elements
- Non-blocking I/O
- Backpressure support

**Example:**
```java
@RestController
public class ReactiveController {
    @GetMapping("/artists")
    public Flux<Artist> getArtists() {
        return Flux.fromIterable(artistService.findAll());
    }
}
```

---

## Key Annotations Quick Reference

| Annotation | Purpose |
|------------|---------|
| `@SpringBootApplication` | Main Spring Boot class |
| `@Component` | Generic Spring bean |
| `@Service` | Service layer bean |
| `@Repository` | Data access bean |
| `@Controller` | Web controller (MVC) |
| `@RestController` | REST controller |
| `@Autowired` | Dependency injection |
| `@Qualifier` | Bean selection by name |
| `@Configuration` | Configuration class |
| `@Bean` | Bean definition in config |
| `@Scope` | Bean scope |
| `@PostConstruct` | Init method |
| `@PreDestroy` | Destroy method |
| `@Transactional` | Transaction boundaries |
| `@GetMapping` | HTTP GET mapping |
| `@PostMapping` | HTTP POST mapping |
| `@PutMapping` | HTTP PUT mapping |
| `@DeleteMapping` | HTTP DELETE mapping |
| `@PathVariable` | URL variable |
| `@RequestParam` | Query parameter |
| `@RequestBody` | Request body to object |
| `@ResponseBody` | Object to response body |

---

## Troubleshooting Common Issues

### Issue 1: Bean Not Found
**Cause:** Component not scanned or missing annotation

**Solution:**
```java
// Ensure package is scanned
@ComponentScan(basePackages = "com.myapp")
```

### Issue 2: Circular Dependency
**Cause:** Bean A depends on B and B depends on A

**Solution:**
- Use constructor injection
- Use @Lazy on one dependency
- Refactor design

### Issue 3: Transaction Not Working
**Cause:** Internal method call bypasses proxy

**Solution:**
- Self-injection
- Separate class
- Move transactional logic to separate method

### Issue 4: Security Configuration Not Applied
**Cause:** Configuration not loaded

**Solution:**
- Ensure @Configuration and @EnableWebSecurity
- Check component scanning
- Verify SecurityFilterChain bean

---

## Best Practices

1. **Use Constructor Injection** over field injection
2. **Prefer Interfaces** for loose coupling
3. **Keep Controllers Thin** - delegate to services
4. **Use @Transactional** at service layer
5. **Write Tests** for all components
6. **Use Profiles** for environment-specific config
7. **Leverage Spring Boot** for auto-configuration
8. **Follow REST conventions** for APIs
9. **Secure endpoints** with Spring Security
10. **Use Spring Data** for data access

---

## Key Takeaways for Interviews

1. **Understand the "Why"** - Spring was created to solve EJB complexity
2. **Dependency Injection** - Core concept, explain with examples
3. **Configuration Options** - XML, Annotations, Java Config
4. **Lifecycle Management** - Scopes and callbacks
5. **Testing** - Spring Boot testing support
6. **Data Access** - JdbcTemplate, JPA, MongoDB
7. **Security** - Authentication and Authorization
8. **Batch Processing** - Spring Batch for large datasets
9. **Modern Practices** - Modulith for modular design
10. **Reactive Programming** - WebFlux for high performance

> "Spring was designed to be simple, productive, object-oriented, business-focused, empirical, and testable."
