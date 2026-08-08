# Modern API Development with Spring 6 and Spring Boot 3 - Interview Q&A Guide

---

## Part 1: RESTful Web Services Fundamentals

### Q1: What is REST and what are its key principles?

**Answer:**

REST (Representational State Transfer) is an architectural style for designing distributed systems, particularly web services. It was introduced by Roy Fielding in his doctoral dissertation in 2000.

**Key Principles:**

**1. Client-Server Architecture**
- Separation of concerns between client and server
- Client handles user interface, server handles data storage
- Enables independent evolution of both components

**2. Statelessness**
- Each request contains all information needed to process it
- Server doesn't maintain client state between requests
- Improves scalability and reliability

**Example:**
```http
GET /api/v1/carts/123 HTTP/1.1
Host: api.ecommerce.com
Authorization: Bearer eyJhbGciOiJIUzI1NiIs...
```
Each request must include authentication, even if the user just authenticated.

**3. Cacheability**
- Responses must explicitly state cacheability
- Improves performance and reduces server load

**4. Uniform Interface**
- Resources are identified by URIs
- Manipulation through representations
- Self-descriptive messages
- HATEOAS (Hypermedia as the Engine of Application State)

**5. Layered System**
- Client cannot assume direct connection to server
- Intermediary layers (proxies, gateways) can be added
- Improves scalability and security

**6. Code on Demand (Optional)**
- Server can extend client functionality
- Example: JavaScript execution

### Q2: Explain the difference between REST and SOAP.

**Answer:**

| Aspect | REST | SOAP |
|--------|------|------|
| **Protocol** | Uses HTTP protocol | Uses multiple protocols (HTTP, SMTP, TCP) |
| **Message Format** | JSON, XML, HTML, plain text | XML only |
| **State** | Stateless | Can maintain state |
| **Security** | HTTPS, JWT, OAuth2 | WS-Security (built-in) |
| **Performance** | Lighter, faster | Heavier, slower |
| **Standards** | No formal standard (architectural style) | Formal specification |
| **Caching** | Built-in HTTP caching | No built-in caching |
| **Development** | Simpler | Complex |

**Example:**
```xml
<!-- SOAP Request -->
<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope">
  <soap:Header>
    <auth:Authentication xmlns:auth="http://example.com/auth">
      <auth:username>user</auth:username>
      <auth:password>pass</auth:password>
    </auth:Authentication>
  </soap:Header>
  <soap:Body>
    <GetUserRequest xmlns="http://example.com/user">
      <userId>123</userId>
    </GetUserRequest>
  </soap:Body>
</soap:Envelope>
```

### Q3: What are HTTP status codes and when should each be used?

**Answer:**

**1xx - Informational Responses**
- **100 Continue**: Client should continue request
- **101 Switching Protocols**: Server switching to different protocol

**2xx - Success Responses**
- **200 OK**: Standard success response
- **201 Created**: Resource successfully created
- **202 Accepted**: Request accepted but processing not complete
- **204 No Content**: Success but no response body

**3xx - Redirects**
- **301 Moved Permanently**: Resource URL changed
- **304 Not Modified**: Use cached version

**4xx - Client Errors**
- **400 Bad Request**: Malformed request
- **401 Unauthorized**: Authentication required
- **403 Forbidden**: Authentication successful but insufficient permissions
- **404 Not Found**: Resource doesn't exist
- **405 Method Not Allowed**: HTTP method not supported
- **409 Conflict**: Resource conflict (duplicate creation)
- **429 Too Many Requests**: Rate limiting exceeded

**5xx - Server Errors**
- **500 Internal Server Error**: Generic server error
- **502 Bad Gateway**: Upstream service failed
- **503 Service Unavailable**: Server overloaded or down

**Example:**
```java
@RestController
public class OrderController {
    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        try {
            Order saved = orderService.create(order);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().build();
        } catch (DuplicateResourceException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
```

### Q4: What is HATEOAS and why is it important?

**Answer:**

HATEOAS (Hypermedia as the Engine of Application State) is a REST constraint where responses contain links to other related resources, allowing clients to navigate the API dynamically.

**Benefits:**
1. **Discoverability**: Clients can discover available actions
2. **Decoupling**: Clients don't hardcode URLs
3. **Evolvability**: APIs can change URLs without breaking clients
4. **Self-documenting**: Response documents itself

**Example:**
```json
{
  "id": "cart-123",
  "customerId": "user-456",
  "items": [
    {
      "id": "item-1",
      "productId": "prod-789",
      "quantity": 2,
      "price": 29.99
    }
  ],
  "_links": {
    "self": {
      "href": "http://api.ecommerce.com/api/v1/carts/123"
    },
    "checkout": {
      "href": "http://api.ecommerce.com/api/v1/carts/123/checkout"
    },
    "customer": {
      "href": "http://api.ecommerce.com/api/v1/users/456"
    },
    "items": {
      "href": "http://api.ecommerce.com/api/v1/carts/123/items"
    }
  }
}
```

**Implementation in Spring:**
```java
@Component
public class CartRepresentationModelAssembler 
    extends RepresentationModelAssemblerSupport<CartEntity, Cart> {
    
    @Override
    public Cart toModel(CartEntity entity) {
        Cart resource = new Cart();
        BeanUtils.copyProperties(entity, resource);
        
        // Self link
        resource.add(linkTo(methodOn(CartsController.class)
            .getCartByCustomerId(entity.getUser().getId()))
            .withSelfRel());
        
        // Checkout link
        resource.add(linkTo(methodOn(CartsController.class)
            .checkout(entity.getUser().getId()))
            .withRel("checkout"));
        
        // Customer link
        resource.add(linkTo(methodOn(UsersController.class)
            .getUser(entity.getUser().getId()))
            .withRel("customer"));
        
        return resource;
    }
}
```

---

## Part 2: Spring Framework Core Concepts

### Q5: What is Dependency Injection and what are its benefits?

**Answer:**

Dependency Injection (DI) is a design pattern where objects receive their dependencies from an external source rather than creating them internally. It's a form of Inversion of Control (IoC).

**Types of DI:**
1. **Constructor Injection** (Recommended)
2. **Setter Injection**
3. **Field Injection**

**Benefits:**
- **Loose Coupling**: Classes depend on interfaces, not concrete implementations
- **Testability**: Easy to mock dependencies
- **Flexibility**: Can swap implementations easily
- **Maintainability**: Clear dependency graph

**Example:**
```java
// Tight coupling - Hard to test
public class OrderService {
    private OrderRepository repository;
    
    public OrderService() {
        this.repository = new OrderRepositoryImpl(); // Hard dependency
    }
}

// Loose coupling - Constructor injection
@Service
public class OrderService {
    private final OrderRepository repository;
    private final PaymentService paymentService;
    private final EmailService emailService;
    
    @Autowired
    public OrderService(
            OrderRepository repository,
            PaymentService paymentService,
            EmailService emailService) {
        this.repository = repository;
        this.paymentService = paymentService;
        this.emailService = emailService;
    }
}

// Test example
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private OrderRepository repository;
    
    @Mock
    private PaymentService paymentService;
    
    @Mock
    private EmailService emailService;
    
    @InjectMocks
    private OrderService orderService;
    
    @Test
    void shouldProcessOrder() {
        // Easy to test with mocks
    }
}
```

### Q6: What are Spring Bean scopes and when would you use each?

**Answer:**

**1. Singleton (Default)**
- Single instance per Spring container
- Best for stateless services
- Memory efficient

**2. Prototype**
- New instance each time requested
- Best for stateful beans
- Higher memory usage

**3. Request**
- New instance per HTTP request
- Web-aware context only
- Best for request-specific data

**4. Session**
- New instance per HTTP session
- Web-aware context only
- Best for user session data

**5. Application**
- Single instance per ServletContext
- Web-aware context only

**6. WebSocket**
- Single instance per WebSocket session
- Web-aware context only

**Example:**
```java
@Configuration
public class AppConfig {
    // Singleton (default)
    @Bean
    public UserService userService() {
        return new UserService();
    }
    
    // Prototype
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ShoppingCart shoppingCart() {
        return new ShoppingCart();
    }
    
    // Request
    @Bean
    @RequestScope
    public RequestContext requestContext() {
        return new RequestContext();
    }
    
    // Session
    @Bean
    @SessionScope
    public UserSession userSession() {
        return new UserSession();
    }
}

@Service
public class OrderService {
    private final ShoppingCart cart; // New instance per request
    private final UserSession session; // Same instance per session
    
    @Autowired
    public OrderService(ShoppingCart cart, UserSession session) {
        this.cart = cart;
        this.session = session;
    }
}
```

### Q7: Explain Aspect-Oriented Programming (AOP) with an example.

**Answer:**

AOP (Aspect-Oriented Programming) is a programming paradigm that separates cross-cutting concerns (logging, security, transactions, monitoring) from business logic, improving modularity and maintainability.

**Key Concepts:**
- **Aspect**: Module containing cross-cutting concern logic
- **Joinpoint**: Point in code where advice is applied
- **Pointcut**: Expression selecting joinpoints
- **Advice**: Action taken at joinpoint
- **Weaving**: Process of applying aspects

**Advice Types:**
- **@Before**: Before method execution
- **@After**: After method execution (success or failure)
- **@AfterReturning**: After successful method execution
- **@AfterThrowing**: After method throws exception
- **@Around**: Before and after method execution

**Example:**
```java
// Custom annotation
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TrackExecution { }

// Aspect implementation
@Aspect
@Component
@Slf4j
public class PerformanceAspect {
    
    @Around("@annotation(TrackExecution)")
    public Object measureExecution(ProceedingJoinPoint joinPoint) 
            throws Throwable {
        long start = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().toShortString();
        
        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - start;
            log.info("Method {} executed in {} ms", methodName, duration);
            return result;
        } catch (Throwable t) {
            long duration = System.currentTimeMillis() - start;
            log.error("Method {} failed after {} ms: {}", 
                methodName, duration, t.getMessage());
            throw t;
        }
    }
    
    @Before("execution(* com.example.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.debug("Entering: {}", joinPoint.getSignature().getName());
    }
}

// Usage
@Service
public class PaymentService {
    @TrackExecution
    public PaymentResponse processPayment(PaymentRequest request) {
        // Business logic
        // Performance automatically measured
    }
}
```

### Q8: What is the difference between @Component, @Service, @Repository, and @Controller?

**Answer:**

**1. @Component**
- Generic stereotype for any Spring-managed bean
- Used when no specific purpose fits

**2. @Service**
- Specialization of @Component for business logic
- Indicates service layer
- Adds semantic meaning

**3. @Repository**
- Specialization of @Component for data access
- Provides exception translation
- Converts database exceptions to Spring DataAccessException

**4. @Controller**
- Specialization of @Component for web layer
- Handles HTTP requests
- Returns views (ModelAndView)

**5. @RestController**
- Specialization of @Controller for REST APIs
- Combines @Controller and @ResponseBody
- Returns data directly (JSON/XML)

**Example:**
```java
@Repository
public class OrderRepository {
    // Data access logic
    // Exception translation automatically applied
    @PersistenceContext
    private EntityManager entityManager;
    
    public Order findById(Long id) {
        try {
            return entityManager.find(Order.class, id);
        } catch (PersistenceException e) {
            // Automatically translated to Spring DataAccessException
            throw e;
        }
    }
}

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository repository;
    
    // Business logic
    public Order placeOrder(OrderRequest request) {
        // Validate
        // Process
        // Save
        return repository.save(order);
    }
}

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService service;
    
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request) {
        Order order = service.placeOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
```

---

## Part 3: API Development with Spring Boot

### Q9: What is OpenAPI Specification and how does it help in API development?

**Answer:**

OpenAPI Specification (formerly Swagger) is a standard, language-agnostic format for describing REST APIs. It enables design-first API development and provides numerous benefits.

**Benefits:**
1. **Documentation**: Automatically generates API documentation
2. **Code Generation**: Generates client SDKs and server stubs
3. **Validation**: Validates API structure and consistency
4. **Testing**: Provides interactive API testing (Swagger UI)
5. **Collaboration**: Non-technical stakeholders can review and provide feedback

**Example:**
```yaml
# openapi.yaml
openapi: 3.0.3
info:
  title: E-commerce API
  version: 1.0.0
  description: API for e-commerce application

paths:
  /api/v1/orders:
    get:
      tags:
        - orders
      summary: Get all orders
      parameters:
        - name: customerId
          in: query
          required: false
          schema:
            type: string
      responses:
        200:
          description: Successfully retrieved orders
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/Order'
        400:
          description: Invalid request

components:
  schemas:
    Order:
      type: object
      required:
        - customerId
      properties:
        id:
          type: string
          format: uuid
        customerId:
          type: string
        totalAmount:
          type: number
          format: double
        status:
          type: string
          enum:
            - PENDING
            - PROCESSING
            - COMPLETED
            - CANCELLED
```

**Integration with Spring:**
```gradle
plugins {
    id 'org.hidetake.swagger.generator' version '2.19.2'
}

swaggerSources {
    eStore {
        inputFile = file("${rootDir}/src/main/resources/api/openapi.yaml")
        code {
            language = 'spring'
            configFile = file("${rootDir}/src/main/resources/api/config.json")
            components = [models: true, apis: true, supportingFiles: 'ApiUtil.java']
        }
    }
}
```

### Q10: Explain the Repository pattern and how Spring Data JPA implements it.

**Answer:**

The Repository pattern abstracts data access, providing a consistent interface to interact with various data sources. Spring Data JPA implements this pattern, drastically reducing boilerplate code.

**Key Interfaces:**
- **Repository**: Marked interface
- **CrudRepository**: Basic CRUD operations
- **PagingAndSortingRepository**: Pagination and sorting
- **JpaRepository**: JPA-specific methods (flush, deleteInBatch)

**Example:**
```java
// Entity
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    
    @Column(name = "customer_id", nullable = false)
    private UUID customerId;
    
    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> items = new ArrayList<>();
}

// Repository with custom queries
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    
    // Method naming convention
    List<OrderEntity> findByCustomerId(UUID customerId);
    
    Optional<OrderEntity> findByCustomerIdAndStatus(UUID customerId, OrderStatus status);
    
    // JPQL query
    @Query("SELECT o FROM OrderEntity o " +
           "WHERE o.customerId = :customerId " +
           "AND o.createdAt > :since")
    List<OrderEntity> findRecentOrders(
        @Param("customerId") UUID customerId,
        @Param("since") LocalDateTime since);
    
    // Native SQL
    @Query(value = "SELECT * FROM orders o " +
                   "WHERE o.customer_id = :customerId " +
                   "AND o.created_at > :since",
           nativeQuery = true)
    List<OrderEntity> findRecentOrdersNative(
        @Param("customerId") UUID customerId,
        @Param("since") LocalDateTime since);
    
    // Pagination
    Page<OrderEntity> findByCustomerId(UUID customerId, Pageable pageable);
}

// Usage in service
@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    
    public OrderEntity placeOrder(OrderEntity order) {
        // Business logic
        order.setStatus(OrderStatus.PENDING);
        return orderRepository.save(order);
    }
    
    public Page<OrderEntity> getOrders(UUID customerId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return orderRepository.findByCustomerId(customerId, pageable);
    }
}
```

### Q11: What is the N+1 problem and how do you solve it in JPA?

**Answer:**

The N+1 problem occurs when an ORM like Hibernate executes N additional queries for each entity fetched in the initial query, instead of using a join or batch loading.

**Example of N+1 Problem:**
```java
// Entity relationships
@Entity
public class Order {
    @Id
    private Long id;
    
    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;
}

// This code causes N+1 queries
List<Order> orders = orderRepository.findAll(); // 1 query
for (Order order : orders) {
    System.out.println(order.getItems().size()); // N queries
}
```

**Solutions:**

**1. JOIN FETCH**
```java
@Query("SELECT o FROM Order o JOIN FETCH o.items")
List<Order> findAllWithItems();

// Or
@Query("SELECT o FROM Order o JOIN FETCH o.items WHERE o.id = :id")
Optional<Order> findByIdWithItems(@Param("id") Long id);
```

**2. Entity Graphs**
```java
@Entity
@NamedEntityGraph(name = "Order.withItems", 
    attributeNodes = @NamedAttributeNode("items"))
public class Order {
    @Id
    private Long id;
    
    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;
}

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph("Order.withItems")
    List<Order> findAll();
    
    @EntityGraph("Order.withItems")
    Optional<Order> findById(Long id);
}
```

**3. Batch Fetching**
```yaml
# application.properties
spring.jpa.properties.hibernate.default_batch_fetch_size=20
```

---

## Part 4: Reactive Programming with Spring WebFlux

### Q12: What is reactive programming and when should you use it?

**Answer:**

Reactive programming is an asynchronous programming paradigm that focuses on data streams and the propagation of change. Spring WebFlux implements reactive programming using Project Reactor.

**Key Concepts:**
- **Non-blocking**: Threads don't block while waiting for operations
- **Asynchronous**: Operations complete at a later time
- **Backpressure**: Subscriber controls data flow rate
- **Event-driven**: Reacts to events rather than waiting

**When to Use Reactive:**
1. High-concurrency applications
2. I/O-intensive operations (database calls, external APIs)
3. Streaming data applications
4. Microservices with high throughput requirements
5. IoT applications

**When NOT to Use Reactive:**
1. CPU-intensive operations
2. Simple CRUD applications
3. Small-scale applications
4. When team lacks reactive experience

**Example:**
```java
@RestController
@RequestMapping("/api/reactive/orders")
public class ReactiveOrderController {
    private final ReactiveOrderService service;
    
    // Returns Mono - 0 or 1 element
    @GetMapping("/{id}")
    public Mono<Order> getOrder(@PathVariable String id) {
        return service.findById(id)
            .switchIfEmpty(Mono.error(
                new ResourceNotFoundException("Order not found")));
    }
    
    // Returns Flux - 0 to N elements
    @GetMapping
    public Flux<Order> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.findAll(page, size);
    }
    
    // Streaming responses
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Order> streamOrders() {
        return service.streamOrders()
            .delayElements(Duration.ofSeconds(1));
    }
}

@Service
public class ReactiveOrderService {
    private final ReactiveOrderRepository repository;
    private final PaymentService paymentService;
    
    public Mono<Order> processOrder(OrderRequest request) {
        // Non-blocking operations
        return validateOrder(request)
            .flatMap(validated -> repository.save(validated))
            .flatMap(saved -> {
                // Process payment non-blocking
                return paymentService.processPayment(saved)
                    .map(payment -> updateOrderStatus(saved, payment));
            })
            .onErrorResume(error -> {
                return handleError(error);
            });
    }
}
```

### Q13: What is the difference between Mono and Flux in Project Reactor?

**Answer:**

**Mono:**
- Represents 0 or 1 element
- Used for single-value responses
- Methods: `just()`, `empty()`, `error()`, `fromCallable()`

**Flux:**
- Represents 0 to N elements
- Used for multi-value responses
- Methods: `just()`, `range()`, `fromIterable()`, `fromStream()`

**Comparison:**
```java
// Mono - Single value
Mono<User> user = userRepository.findById(1L);
Mono<String> userName = user.map(User::getName);
Mono<User> fallbackUser = user.defaultIfEmpty(new User("Guest"));

// Flux - Multiple values
Flux<User> users = userRepository.findAll();
Flux<String> userNames = users.map(User::getName);
Flux<User> filtered = users.filter(u -> u.getAge() > 18);

// Combining Mono and Flux
Mono<Order> order = repository.findById(1L);
Flux<OrderItem> items = order
    .flatMapMany(o -> repository.findItemsByOrderId(o.getId()));

// Error handling
Mono<User> error = Mono.error(new RuntimeException("Not found"))
    .onErrorResume(e -> {
        log.error("Error: ", e);
        return Mono.just(new User("Default"));
    });

// Timeout
Mono<User> timeout = repository.findById(1L)
    .timeout(Duration.ofSeconds(5))
    .onErrorReturn(new User("Timeout"));

// Delay
Flux<Order> delayed = Flux.range(1, 5)
    .delayElements(Duration.ofSeconds(1))
    .map(i -> new Order("Order-" + i));
```

### Q14: Explain R2DBC and how it differs from traditional JDBC.

**Answer:**

R2DBC (Reactive Relational Database Connectivity) is a reactive API for relational databases that enables non-blocking database access.

**Key Differences from JDBC:**

| Aspect | JDBC | R2DBC |
|--------|------|-------|
| **Threading** | Blocking, occupies thread | Non-blocking, releases thread |
| **API Style** | Imperative | Reactive (Publisher) |
| **Backpressure** | Not supported | Supported |
| **Performance** | Lower throughput | Higher throughput |
| **Learning Curve** | Lower | Higher |

**Example:**
```java
// Configuration
@Configuration
public class R2dbcConfig {
    @Bean
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(
            "r2dbc:h2:mem:///testdb?options=DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=FALSE"
        );
    }
}

// Entity
@Table("orders")
public class OrderEntity {
    @Id
    private UUID id;
    
    @Column("customer_id")
    private UUID customerId;
    
    @Column("total_amount")
    private BigDecimal totalAmount;
}

// Repository
@Repository
public interface OrderRepository 
        extends ReactiveCrudRepository<OrderEntity, UUID> {
    
    @Query("SELECT * FROM orders WHERE customer_id = :customerId")
    Flux<OrderEntity> findByCustomerId(@Param("customerId") UUID customerId);
}

// Custom repository implementation
@Repository
public class OrderRepositoryImpl implements OrderRepositoryCustom {
    private final DatabaseClient client;
    
    public Mono<OrderEntity> insert(Mono<OrderEntity> orderMono) {
        return orderMono.flatMap(order -> {
            return client.sql("INSERT INTO orders (id, customer_id, total_amount) " +
                              "VALUES ($1, $2, $3)")
                .bind("$1", order.getId())
                .bind("$2", order.getCustomerId())
                .bind("$3", order.getTotalAmount())
                .fetch()
                .rowsUpdated()
                .thenReturn(order);
        });
    }
}

// Service
@Service
public class OrderService {
    private final OrderRepository repository;
    
    public Flux<Order> getOrdersByCustomer(String customerId) {
        return repository.findByCustomerId(UUID.fromString(customerId))
            .map(this::toOrder)
            .switchIfEmpty(Flux.error(
                new ResourceNotFoundException("No orders found")));
    }
}
```

---

## Part 5: Security with Spring Security

### Q15: Explain JWT structure and its components.

**Answer:**

JWT (JSON Web Token) is a compact, URL-safe token format for securely transmitting information between parties.

**Structure:** `header.payload.signature`

**1. Header**
```json
{
    "alg": "RS256",    // Signing algorithm
    "typ": "JWT"       // Token type
}
```

**2. Payload (Claims)**
```json
{
    // Registered claims
    "sub": "1234567890",      // Subject
    "iss": "auth.example.com", // Issuer
    "iat": 1516239022,        // Issued at
    "exp": 1516242622,        // Expiration
    "aud": "api.example.com", // Audience
    
    // Public claims
    "roles": ["USER", "ADMIN"],
    
    // Private claims (custom)
    "userId": "user-123",
    "tenantId": "tenant-456"
}
```

**3. Signature**
```
Signature = RS256(
    base64UrlEncode(header) + "." + base64UrlEncode(payload),
    privateKey
)
```

**Example:**
```java
@Component
public class JwtManager {
    private final RSAPrivateKey privateKey;
    private final RSAPublicKey publicKey;
    
    public String createToken(UserDetails user) {
        return JWT.create()
            .withIssuer("ecommerce-api")
            .withSubject(user.getUsername())
            .withAudience("ecommerce-client")
            .withClaim("roles", user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()))
            .withClaim("userId", getUserId(user))
            .withIssuedAt(new Date())
            .withExpiresAt(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
            .sign(Algorithm.RSA256(publicKey, privateKey));
    }
    
    public Authentication validateToken(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.RSA256(publicKey, privateKey))
            .withIssuer("ecommerce-api")
            .build()
            .verify(token);
        
        // Extract claims
        String username = jwt.getSubject();
        List<String> roles = jwt.getClaim("roles").asList(String.class);
        String userId = jwt.getClaim("userId").asString();
        
        // Create authentication
        return new UsernamePasswordAuthenticationToken(
            new UserPrincipal(userId, username, roles),
            null,
            getAuthorities(roles)
        );
    }
}
```

### Q16: Explain OAuth 2.0 and how Spring Security implements it.

**Answer:**

OAuth 2.0 is an authorization framework that allows third-party applications to access user data without exposing credentials.

**OAuth 2.0 Roles:**
- **Resource Owner**: User who owns the data
- **Client**: Third-party application requesting access
- **Resource Server**: Server hosting protected resources
- **Authorization Server**: Server issuing tokens

**Grant Types:**
1. **Authorization Code** (Most common)
2. **Implicit** (Deprecated)
3. **Resource Owner Password Credentials** (Legacy)
4. **Client Credentials** (Machine-to-machine)

**Example Implementation:**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**", "/oauth2/**").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt
                    .jwtAuthenticationConverter(jwtAuthenticationConverter())
                )
            )
            .oauth2Login(oauth2 -> oauth2
                .loginPage("/login")
                .authorizationEndpoint(endpoint -> endpoint
                    .baseUri("/oauth2/authorization"))
                .redirectionEndpoint(endpoint -> endpoint
                    .baseUri("/login/oauth2/code/*"))
            )
            .build();
    }
    
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthorityPrefix("ROLE_");
        converter.setAuthoritiesClaimName("roles");
        
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(converter);
        return jwtConverter;
    }
    
    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository authorizedClientRepository) {
        OAuth2AuthorizedClientProvider provider = 
            OAuth2AuthorizedClientProviderBuilder.builder()
                .authorizationCode()
                .refreshToken()
                .build();
        
        DefaultOAuth2AuthorizedClientManager manager = 
            new DefaultOAuth2AuthorizedClientManager(
                clientRegistrationRepository, 
                authorizedClientRepository);
        manager.setAuthorizedClientProvider(provider);
        return manager;
    }
}
```

---

## Part 6: gRPC Implementation

### Q17: What are the advantages of gRPC over REST?

**Answer:**

**Advantages of gRPC:**

1. **Performance**
   - Binary serialization (Protocol Buffers)
   - HTTP/2 multiplexing
   - More efficient than JSON/HTTP 1.1

2. **Streaming Support**
   - Server streaming
   - Client streaming
   - Bidirectional streaming

3. **Type Safety**
   - Strongly typed services
   - Contract-first development
   - Automatic code generation

4. **Advanced Features**
   - Built-in load balancing
   - Connection multiplexing
   - Flow control
   - Deadline/timeout propagation

5. **Language Support**
   - Multiple language implementations
   - Cross-language service definitions

**Example Comparison:**
```protobuf
// gRPC - Protobuf definition
service OrderService {
    rpc CreateOrder(OrderRequest) returns (OrderResponse);
    rpc GetOrder(OrderId) returns (Order);
    rpc ListOrders(CustomerId) returns (stream Order);
}

message OrderRequest {
    string customerId = 1;
    repeated OrderItem items = 2;
    string currency = 3;
}
```

```yaml
// REST - OpenAPI definition
paths:
  /api/orders:
    post:
      requestBody:
        content:
          application/json:
            schema:
              type: object
              properties:
                customerId:
                  type: string
                items:
                  type: array
```

**Performance Comparison:**
- gRPC is ~7-10x faster than REST for payload size
- gRPC has 10-20% better throughput under load
- gRPC serialization/deserialization is significantly faster

### Q18: Explain Protocol Buffers and how they work.

**Answer:**

Protocol Buffers (Protobuf) is Google's language-neutral, platform-neutral mechanism for serializing structured data.

**Key Features:**
1. **Language agnostic**: Works with Java, Python, Go, C++, etc.
2. **Compact binary format**: Smaller than JSON or XML
3. **Self-describing**: Message types include field definitions
4. **Backward compatible**: Can add/remove fields safely
5. **Performance**: Faster serialization/deserialization

**Example Definition:**
```protobuf
syntax = "proto3";

package com.example.ecommerce.v1;

option java_package = "com.example.ecommerce.proto";
option java_multiple_files = true;

enum OrderStatus {
    PENDING = 0;
    CONFIRMED = 1;
    SHIPPED = 2;
    DELIVERED = 3;
    CANCELLED = 4;
}

message Order {
    string id = 1;
    string customer_id = 2;
    repeated OrderItem items = 3;
    double total = 4;
    OrderStatus status = 5;
    google.protobuf.Timestamp created_at = 6;
}

message OrderItem {
    string product_id = 1;
    int32 quantity = 2;
    double price = 3;
}

service OrderService {
    rpc CreateOrder(CreateOrderRequest) returns (Order);
    rpc GetOrder(GetOrderRequest) returns (Order);
    rpc ListOrders(ListOrdersRequest) returns (ListOrdersResponse);
}
```

**Serialization Example:**
```
# Order message serialized to binary
Field 1 (id): string "ord-123"
Field 2 (customer_id): string "cust-456"  
Field 3 (items): repeated OrderItem...
Field 4 (total): double 299.99
Field 5 (status): enum OrderStatus.SHIPPED
Field 6 (created_at): timestamp 2024-01-15T10:30:00Z
```

---

## Part 7: Logging and Distributed Tracing

### Q19: What is distributed tracing and why is it important?

**Answer:**

Distributed tracing is a technique for tracking requests across multiple services in a distributed system, providing visibility into the flow and performance of requests.

**Key Concepts:**
- **Trace**: Complete request journey across all services
- **Span**: Individual operation within a service
- **Context Propagation**: Carrying trace information across services
- **Annotation**: Additional metadata (timestamp, events)

**Example Implementation:**
```java
@Configuration
public class TracingConfig {
    @Bean
    public ObservationRegistry observationRegistry() {
        return ObservationRegistry.create();
    }
}

@Service
@Slf4j
public class OrderService {
    
    public Order processOrder(OrderRequest request, Observation observation) {
        return observation.observe(() -> {
            // Validate
            log.info("Validating order");
            validate(request);
            
            // Process payment (calls another service)
            PaymentResponse payment = processPayment(request);
            
            // Create order
            Order order = createOrder(request, payment);
            
            // Send notification
            sendNotification(order);
            
            return order;
        });
    }
}

// gRPC interceptor for tracing
@Component
public class TracingGrpcInterceptor {
    @Bean
    public ObservationGrpcServerInterceptor serverInterceptor(
            ObservationRegistry registry) {
        return new ObservationGrpcServerInterceptor(registry);
    }
    
    @Bean
    public ObservationGrpcClientInterceptor clientInterceptor(
            ObservationRegistry registry) {
        return new ObservationGrpcClientInterceptor(registry);
    }
}
```

**Log Integration:**
```xml
<!-- logback-spring.xml -->
<appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
    <encoder>
        <pattern>
            %d{HH:mm:ss.SSS} %-5level [%X{traceId:-}][%X{spanId:-}] %msg%n
        </pattern>
    </encoder>
</appender>
```

### Q20: What is the ELK stack and how does it help in logging?

**Answer:**

ELK stack (Elasticsearch, Logstash, Kibana) provides centralized logging, searching, and visualization for distributed systems.

**Components:**

1. **Elasticsearch**
   - Document-oriented search engine
   - Stores and indexes logs
   - Provides powerful full-text search

2. **Logstash**
   - Data pipeline tool
   - Collects logs from multiple sources
   - Transforms and ships to Elasticsearch

3. **Kibana**
   - Visualization dashboard
   - Searches and filters logs
   - Creates charts and graphs

**Example Configuration:**
```yaml
# docker-compose.yml
version: '3.8'
services:
  elasticsearch:
    image: docker.elastic.co/elasticsearch/elasticsearch:8.7.0
    environment:
      - discovery.type=single-node
      - xpack.security.enabled=false
    ports:
      - "9200:9200"
    
  logstash:
    image: docker.elastic.co/logstash/logstash:8.7.0
    command: >
      logstash -e '
        input {
          tcp {
            port => 5000
            codec => json
          }
        }
        output {
          elasticsearch {
            hosts => ["elasticsearch:9200"]
            index => "ecommerce-logs-%{+YYYY.MM.dd}"
          }
        }'
    ports:
      - "5000:5000"
    depends_on:
      - elasticsearch
    
  kibana:
    image: docker.elastic.co/kibana/kibana:8.7.0
    environment:
      - ELASTICSEARCH_HOSTS=http://elasticsearch:9200
    ports:
      - "5601:5601"
    depends_on:
      - elasticsearch
```

**Log Integration:**
```xml
<!-- logback-spring.xml -->
<appender name="STASH" 
    class="net.logstash.logback.appender.LogstashTcpSocketAppender">
    <destination>localhost:5000</destination>
    <encoder class="net.logstash.logback.encoder.LogstashEncoder">
        <customFields>
            {
                "application": "ecommerce-service",
                "environment": "production",
                "version": "1.0.0"
            }
        </customFields>
    </encoder>
</appender>
```

---

## Part 8: GraphQL Implementation

### Q21: What is GraphQL and how does it compare to REST?

**Answer:**

GraphQL is a query language and runtime for APIs that enables clients to request exactly the data they need.

**Key Differences from REST:**

| Aspect | GraphQL | REST |
|--------|---------|------|
| **Endpoint** | Single endpoint | Multiple endpoints |
| **Data Fetching** | Client specifies exactly what to fetch | Server defines response structure |
| **Over/Under-fetching** | Eliminated | Common |
| **Versioning** | Schema evolution without versioning | Often requires versioning |
| **Query Language** | GraphQL SDL | HTTP methods |
| **Caching** | Requires external solution | Built-in HTTP caching |
| **Learning Curve** | Steeper | Lower |

**Example:**
```graphql
# GraphQL Query - Get exactly what you need
query GetOrderDetails($orderId: ID!) {
    order(id: $orderId) {
        id
        customer {
            name
            email
        }
        items {
            product {
                name
                price
            }
            quantity
        }
        total
        status
    }
}

# REST Equivalent - Multiple calls needed
GET /api/orders/123
GET /api/customers/456
GET /api/orders/123/items
GET /api/products/789
```

### Q22: Explain GraphQL schema, types, and resolvers.

**Answer:**

**Schema**: Defines the API contract, including available operations and types.

**Types:**
1. **Scalar Types**: Int, Float, String, Boolean, ID
2. **Object Types**: Custom types with fields
3. **Interface**: Abstract type with common fields
4. **Union**: Multiple types combined
5. **Enum**: Predefined set of values
6. **Input**: Object type for arguments

**Example:**
```graphql
# Schema
type Query {
    orders(customerId: ID!): [Order!]!
    order(id: ID!): Order
}

type Mutation {
    createOrder(input: OrderInput!): Order!
    updateOrderStatus(id: ID!, status: OrderStatus!): Order!
}

type Subscription {
    orderUpdated(orderId: ID!): Order!
}

type Order {
    id: ID!
    customerId: ID!
    items: [OrderItem!]!
    total: Float!
    status: OrderStatus!
    createdAt: String!
}

type OrderItem {
    productId: ID!
    quantity: Int!
    price: Float!
}

enum OrderStatus {
    PENDING
    CONFIRMED
    SHIPPED
    DELIVERED
    CANCELLED
}

input OrderInput {
    customerId: ID!
    items: [OrderItemInput!]!
    shippingAddress: AddressInput!
}

input OrderItemInput {
    productId: ID!
    quantity: Int!
}
```

**Resolvers (Data Fetchers):**
```java
@DgsComponent
public class OrderDataFetcher {
    private final OrderService orderService;
    
    @DgsQuery
    public List<Order> orders(@InputArgument("customerId") String customerId) {
        return orderService.getOrders(customerId);
    }
    
    @DgsQuery
    public Order order(@InputArgument("id") String id) {
        return orderService.getOrder(id);
    }
    
    @DgsMutation
    public Order createOrder(@InputArgument("input") OrderInput input) {
        return orderService.createOrder(input);
    }
    
    @DgsData(parentType = "Order", field = "items")
    public CompletableFuture<List<OrderItem>> items(
            DgsDataFetchingEnvironment env) {
        Order order = env.getSource();
        DataLoader<String, List<OrderItem>> loader = 
            env.getDataLoader(OrderItemLoader.class);
        return loader.load(order.getId());
    }
}

// DataLoader to solve N+1 problem
@DgsDataLoader(name = "orderItemLoader")
public class OrderItemLoader 
        implements MappedBatchLoader<String, List<OrderItem>> {
    
    private final OrderItemRepository repository;
    
    @Override
    public CompletionStage<Map<String, List<OrderItem>>> load(
            Set<String> keys, BatchLoaderEnvironment env) {
        return CompletableFuture.supplyAsync(() -> {
            List<OrderItem> items = repository.findByOrderIds(new ArrayList<>(keys));
            Map<String, List<OrderItem>> map = items.stream()
                .collect(Collectors.groupingBy(OrderItem::getOrderId));
            return keys.stream()
                .map(key -> Map.entry(key, map.getOrDefault(key, List.of())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        });
    }
}
```

### Q23: What is the N+1 problem in GraphQL and how do you solve it?

**Answer:**

The N+1 problem in GraphQL occurs when a resolver makes one query to fetch a list of N items, and then makes N additional queries to fetch related data for each item.

**Example of N+1 Problem:**
```graphql
# Query that causes N+1
{
    orders {
        id
        customer {
            name  # This will cause N queries
        }
    }
}
```

**Solution - Data Loader:**
```java
@DgsComponent
public class CustomerDataFetcher {
    @DgsData(parentType = "Order", field = "customer")
    public CompletableFuture<Customer> customer(
            DgsDataFetchingEnvironment env) {
        Order order = env.getSource();
        
        // DataLoader batches all requests
        DataLoader<String, Customer> loader = 
            env.getDataLoader(CustomerLoader.class);
        return loader.load(order.getCustomerId());
    }
}

@DgsDataLoader(name = "customerLoader")
public class CustomerLoader implements MappedBatchLoader<String, Customer> {
    private final CustomerRepository repository;
    
    @Override
    public CompletionStage<Map<String, Customer>> load(
            Set<String> keys, BatchLoaderEnvironment env) {
        return CompletableFuture.supplyAsync(() -> {
            // Single query to fetch all customers
            List<Customer> customers = 
                repository.findByCustomerIds(new ArrayList<>(keys));
            
            return customers.stream()
                .collect(Collectors.toMap(Customer::getId, Function.identity()));
        });
    }
}
```

### Q24: Explain GraphQL subscriptions and their use cases.

**Answer:**

GraphQL subscriptions enable real-time data updates by maintaining a WebSocket connection between client and server.

**Use Cases:**
1. Live updates (scores, prices, weather)
2. Notifications (order status, messages)
3. Real-time monitoring
4. Collaborative features

**Implementation:**
```java
// Schema
type Subscription {
    orderStatusChanged(customerId: ID!): Order!
    inventoryUpdated(productId: ID!): Product!
    orderShipped(orderId: ID!): Shipping!
}

// Server Implementation
@DgsComponent
public class SubscriptionDataFetcher {
    private final FluxSink<Order> orderSink;
    private final Flux<Order> orderFlux;
    
    public SubscriptionDataFetcher() {
        Flux<Order> publisher = Flux.create(emitter -> {
            orderSink = emitter;
        });
        orderFlux = publisher.publish().autoConnect();
    }
    
    @DgsSubscription
    public Publisher<Order> orderStatusChanged(
            @InputArgument("customerId") String customerId) {
        return orderFlux
            .filter(order -> order.getCustomerId().equals(customerId))
            .subscribeOn(Schedulers.boundedElastic());
    }
    
    public void notifyOrderStatusChanged(Order order) {
        if (orderSink != null) {
            orderSink.next(order); // Push update to all subscribers
        }
    }
}

// Client Subscription
subscription SubscribeToOrderUpdates($customerId: ID!) {
    orderStatusChanged(customerId: $customerId) {
        id
        status
        updatedAt
        items {
            productId
            quantity
        }
    }
}
```

---

## Interview Tips Summary

### Most Important Topics:
1. **REST API design principles** - HATEOAS, status codes, versioning
2. **Spring Core** - DI, IoC, Bean scopes, AOP
3. **Spring Data JPA** - Repository pattern, entity relationships, N+1 problem
4. **Spring Security** - JWT, OAuth2, RBAC, CORS, CSRF
5. **Reactive Programming** - Mono/Flux, WebFlux, R2DBC
6. **gRPC** - Protocol Buffers, streaming, performance benefits
7. **GraphQL** - Schema design, resolvers, N+1 problem, subscriptions
8. **Distributed Systems** - Tracing, logging, ELK stack

### Common Pitfalls to Avoid:
1. Ignoring HATEOAS in REST APIs
2. N+1 problem in JPA and GraphQL
3. Blocking calls in reactive streams
4. Using wrong bean scopes
5. Misunderstanding JWT claims and validation
6. Not considering API versioning strategy

### Advanced Topics to Mention:
1. API Gateway pattern
2. Circuit Breaker pattern
3. CQRS with GraphQL
4. Federation in GraphQL
5. Observability (metrics, traces, logs)
