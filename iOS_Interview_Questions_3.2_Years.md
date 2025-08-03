# iOS Developer Interview Questions & Answers
## For 3.2 Years Experience Candidate

---

## Table of Contents
1. [Swift Language Fundamentals](#swift-language-fundamentals)
2. [iOS App Architecture](#ios-app-architecture)
3. [UIKit & Interface Development](#uikit--interface-development)
4. [Data Management & Persistence](#data-management--persistence)
5. [Networking & APIs](#networking--apis)
6. [Memory Management](#memory-management)
7. [Concurrency & Threading](#concurrency--threading)
8. [Testing & Debugging](#testing--debugging)
9. [Performance Optimization](#performance-optimization)
10. [App Store & Deployment](#app-store--deployment)

---

## Swift Language Fundamentals

### 1. What are optionals in Swift and why are they important?
**Answer:** Optionals are types that can hold either a value or nil. They're important because they provide type safety and help prevent runtime crashes by making the possibility of nil explicit in the type system.

```swift
var name: String? = "John"
var age: Int? = nil

// Safe unwrapping
if let unwrappedName = name {
    print("Name is \(unwrappedName)")
}
```

### 2. Explain the difference between weak and strong references.
**Answer:** 
- **Strong references** keep objects in memory and increment the reference count
- **Weak references** don't increment reference count and automatically become nil when the referenced object is deallocated
- Used to break retain cycles and prevent memory leaks

```swift
class Person {
    weak var delegate: PersonDelegate?
    var name: String
}
```

### 3. What is the difference between `let` and `var`?
**Answer:**
- `let` creates immutable constants - value cannot be changed after initialization
- `var` creates mutable variables - value can be modified
- Use `let` by default for better performance and safety

### 4. Explain closures in Swift.
**Answer:** Closures are self-contained blocks of functionality that can be passed around and used in code. They're similar to lambdas in other languages.

```swift
let numbers = [1, 2, 3, 4, 5]
let doubled = numbers.map { $0 * 2 }

// Trailing closure syntax
UIView.animate(withDuration: 0.3) {
    self.view.alpha = 0.5
}
```

### 5. What are generics in Swift?
**Answer:** Generics enable you to write flexible, reusable functions and types that can work with any type, subject to requirements you define.

```swift
func swapTwoValues<T>(_ a: inout T, _ b: inout T) {
    let temporaryA = a
    a = b
    b = temporaryA
}
```

### 6. Explain protocols in Swift.
**Answer:** Protocols define a blueprint of methods, properties, and other requirements that suit a particular task or piece of functionality.

```swift
protocol Drawable {
    func draw()
}

class Circle: Drawable {
    func draw() {
        print("Drawing a circle")
    }
}
```

### 7. What is the difference between `class` and `struct`?
**Answer:**
- **Classes:** Reference types, support inheritance, can have deinitializers
- **Structs:** Value types, no inheritance, automatically get memberwise initializers
- Use structs for simple data models, classes for complex objects with behavior

### 8. What are extensions in Swift?
**Answer:** Extensions add new functionality to existing classes, structures, enumerations, or protocol types without modifying their original implementation.

```swift
extension String {
    func isValidEmail() -> Bool {
        // Email validation logic
        return self.contains("@")
    }
}
```

### 9. Explain the `guard` statement.
**Answer:** Guard statements provide early exit from a function when conditions aren't met, improving code readability and reducing nesting.

```swift
func processUser(name: String?) {
    guard let userName = name, !userName.isEmpty else {
        print("Invalid name")
        return
    }
    // Continue with valid userName
}
```

### 10. What is type inference in Swift?
**Answer:** Swift can automatically deduce the type of a variable or constant based on the initial value provided, reducing the need for explicit type annotations.

```swift
let message = "Hello" // Inferred as String
let count = 42 // Inferred as Int
```

### 11. Explain the `defer` statement.
**Answer:** Defer statements are used to execute code just before transferring program control outside of the scope that contains the defer statement.

```swift
func processFile() {
    let file = openFile()
    defer {
        closeFile(file)
    }
    // File operations
} // closeFile is called here
```

### 12. What are associated values in enums?
**Answer:** Associated values allow you to store additional custom information alongside enum case values.

```swift
enum NetworkResponse {
    case success(Data)
    case failure(Error)
}
```

### 13. Explain the `@escaping` keyword.
**Answer:** @escaping indicates that a closure parameter can "escape" the function - be called after the function returns.

```swift
func performAsync(completion: @escaping () -> Void) {
    DispatchQueue.main.asyncAfter(deadline: .now() + 1) {
        completion() // Called after function returns
    }
}
```

### 14. What is the difference between `map`, `filter`, and `reduce`?
**Answer:**
- **map:** Transforms each element and returns a new array
- **filter:** Returns elements that satisfy a condition
- **reduce:** Combines all elements into a single value

```swift
let numbers = [1, 2, 3, 4, 5]
let doubled = numbers.map { $0 * 2 }
let evens = numbers.filter { $0 % 2 == 0 }
let sum = numbers.reduce(0, +)
```

### 15. What are property observers?
**Answer:** Property observers observe and respond to changes in a property's value.

```swift
var score: Int = 0 {
    willSet {
        print("About to set score to \(newValue)")
    }
    didSet {
        print("Score changed from \(oldValue) to \(score)")
    }
}
```

---

## iOS App Architecture

### 16. Explain the MVC pattern in iOS.
**Answer:** Model-View-Controller separates app logic into three interconnected components:
- **Model:** Data and business logic
- **View:** User interface elements
- **Controller:** Mediates between Model and View

### 17. What is MVVM architecture?
**Answer:** Model-View-ViewModel pattern where:
- **Model:** Data layer
- **View:** UI layer (UIView, UIViewController)
- **ViewModel:** Presentation logic, binds Model to View

### 18. Explain the VIPER architecture.
**Answer:** VIPER stands for View, Interactor, Presenter, Entity, Router:
- **View:** UI logic
- **Interactor:** Business logic
- **Presenter:** View logic
- **Entity:** Data objects
- **Router:** Navigation logic

### 19. What is dependency injection?
**Answer:** A design pattern where dependencies are provided to an object rather than the object creating them itself, improving testability and flexibility.

```swift
class NetworkService {
    let urlSession: URLSession
    
    init(urlSession: URLSession = .shared) {
        self.urlSession = urlSession
    }
}
```

### 20. Explain the Singleton pattern.
**Answer:** Ensures a class has only one instance and provides global access to it.

```swift
class UserManager {
    static let shared = UserManager()
    private init() {}
    
    var currentUser: User?
}
```

### 21. What is the Observer pattern?
**Answer:** Defines a one-to-many dependency between objects so that when one object changes state, all dependents are notified.

```swift
protocol UserObserver: AnyObject {
    func userDidLogin(_ user: User)
}

class UserManager {
    private var observers: [UserObserver] = []
    
    func addObserver(_ observer: UserObserver) {
        observers.append(observer)
    }
}
```

### 22. Explain the Factory pattern.
**Answer:** Creates objects without specifying their exact classes, providing an interface for creating families of related objects.

```swift
protocol Vehicle {
    func start()
}

class VehicleFactory {
    static func createVehicle(type: VehicleType) -> Vehicle {
        switch type {
        case .car: return Car()
        case .bike: return Bike()
        }
    }
}
```

### 23. What is the Coordinator pattern?
**Answer:** Handles navigation flow and removes navigation responsibility from view controllers, making them more focused and testable.

```swift
protocol Coordinator {
    func start()
    func coordinate(to coordinator: Coordinator)
}
```

### 24. Explain Protocol-Oriented Programming.
**Answer:** A paradigm that emphasizes protocols over inheritance, promoting composition over inheritance and enabling more flexible and testable code.

### 25. What is the Repository pattern?
**Answer:** Abstracts data access logic and provides a uniform interface for accessing data from different sources.

```swift
protocol UserRepository {
    func fetchUsers() -> [User]
    func saveUser(_ user: User)
}

class CoreDataUserRepository: UserRepository {
    func fetchUsers() -> [User] {
        // Core Data implementation
    }
}
```

---

## UIKit & Interface Development

### 26. Explain the view lifecycle methods.
**Answer:** UIViewController lifecycle methods:
- `viewDidLoad`: View loaded into memory
- `viewWillAppear`: View about to appear
- `viewDidAppear`: View appeared
- `viewWillDisappear`: View about to disappear
- `viewDidDisappear`: View disappeared

### 27. What is Auto Layout?
**Answer:** A constraint-based layout system that allows you to create adaptive interfaces that respond to different screen sizes and orientations.

### 28. Explain the difference between frame and bounds.
**Answer:**
- **frame:** Rectangle in superview's coordinate system
- **bounds:** Rectangle in view's own coordinate system
- Frame changes with position, bounds typically starts at (0,0)

### 29. What are IBOutlets and IBActions?
**Answer:**
- **IBOutlet:** Connection between code and interface element for accessing properties
- **IBAction:** Connection for handling user interactions like button taps

### 30. Explain the responder chain.
**Answer:** The hierarchy through which iOS passes events (touches, motion, etc.) from the initial receiver up through parent views until handled.

### 31. What is the difference between UIView and UIControl?
**Answer:**
- **UIView:** Base class for all visual elements
- **UIControl:** Subclass of UIView that can respond to user interactions and send action messages

### 32. Explain table view cell reuse.
**Answer:** iOS reuses table view cells to optimize memory usage. Cells that scroll off-screen are queued for reuse instead of being deallocated.

```swift
let cell = tableView.dequeueReusableCell(withIdentifier: "CellIdentifier", for: indexPath)
```

### 33. What are size classes?
**Answer:** Traits that characterize the amount of display space available in horizontal and vertical dimensions (compact, regular).

### 34. Explain the difference between `addSubview` and `insertSubview`.
**Answer:**
- `addSubview`: Adds view as the topmost subview
- `insertSubview`: Adds view at a specific index in the subview hierarchy

### 35. What is content hugging and compression resistance?
**Answer:**
- **Content Hugging:** How much a view resists being stretched beyond its intrinsic content size
- **Compression Resistance:** How much a view resists being compressed smaller than its intrinsic content size

### 36. Explain stack views.
**Answer:** UIStackView automatically manages the layout of arranged subviews in either a column or row, handling spacing and alignment.

### 37. What is the intrinsic content size?
**Answer:** The natural size for a view based on its content, like the size of a label based on its text.

### 38. Explain the difference between modal and push presentation.
**Answer:**
- **Modal:** Presents view controller on top of current context
- **Push:** Adds view controller to navigation stack

### 39. What are storyboards and XIB files?
**Answer:**
- **Storyboard:** Visual representation of app's user interface showing view controllers and transitions
- **XIB:** Individual interface file for a single view or view controller

### 40. Explain safe area.
**Answer:** The area of a view that is guaranteed to not be obscured by navigation bars, tab bars, or device-specific features like the notch.

---

## Data Management & Persistence

### 41. What is Core Data?
**Answer:** Apple's object graph and persistence framework for managing the model layer objects in your application.

### 42. Explain the Core Data stack.
**Answer:** Components include:
- **NSManagedObjectModel:** Schema definition
- **NSPersistentStoreCoordinator:** Manages persistent stores
- **NSManagedObjectContext:** Scratchpad for managed objects
- **NSPersistentContainer:** Encapsulates the entire stack

### 43. What is an NSFetchRequest?
**Answer:** A description of search criteria used to retrieve data from a persistent store.

```swift
let request: NSFetchRequest<Person> = Person.fetchRequest()
request.predicate = NSPredicate(format: "age > %d", 18)
```

### 44. Explain NSPredicate.
**Answer:** A logical condition used to constrain a search for fetch requests or in-memory filtering.

### 45. What is UserDefaults?
**Answer:** A user's defaults database where you store key-value pairs persistently across app launches.

```swift
UserDefaults.standard.set("John", forKey: "username")
let username = UserDefaults.standard.string(forKey: "username")
```

### 46. Explain the difference between UserDefaults and Keychain.
**Answer:**
- **UserDefaults:** For non-sensitive user preferences, stored in plist format
- **Keychain:** For sensitive data like passwords, encrypted and secure

### 47. What is SQLite?
**Answer:** A lightweight, embedded SQL database engine that Core Data can use as its underlying storage.

### 48. Explain data migration in Core Data.
**Answer:** Process of updating your data model and migrating existing data when you change your Core Data schema.

### 49. What are NSManagedObject subclasses?
**Answer:** Custom classes that represent Core Data entities, providing type-safe access to attributes and relationships.

### 50. Explain the difference between save and rollback in Core Data.
**Answer:**
- **save:** Commits changes to the persistent store
- **rollback:** Discards all changes since the last save

---

## Networking & APIs

### 51. What is URLSession?
**Answer:** Foundation framework's API for downloading and uploading data to and from network endpoints.

```swift
let task = URLSession.shared.dataTask(with: url) { data, response, error in
    // Handle response
}
task.resume()
```

### 52. Explain the different types of URLSession tasks.
**Answer:**
- **Data tasks:** Send and receive data using NSData objects
- **Upload tasks:** Upload files and data to web services
- **Download tasks:** Download files from web services

### 53. What is JSON parsing in iOS?
**Answer:** Converting JSON data into Swift objects using JSONSerialization or Codable protocol.

```swift
struct User: Codable {
    let name: String
    let email: String
}

let user = try JSONDecoder().decode(User.self, from: data)
```

### 54. Explain the Codable protocol.
**Answer:** A type alias for Encodable and Decodable protocols that enables automatic JSON serialization/deserialization.

### 55. What is Alamofire?
**Answer:** A popular third-party HTTP networking library that simplifies common networking tasks in iOS.

### 56. Explain REST APIs.
**Answer:** Representational State Transfer - architectural style for web services using standard HTTP methods (GET, POST, PUT, DELETE).

### 57. What is authentication in mobile apps?
**Answer:** Process of verifying user identity, commonly using tokens, OAuth, or biometric authentication.

### 58. Explain error handling in networking.
**Answer:** Properly handling network errors, timeouts, and invalid responses to provide good user experience.

```swift
enum NetworkError: Error {
    case invalidURL
    case noData
    case decodingError
}
```

### 59. What is a completion handler?
**Answer:** A closure that gets called when an asynchronous operation completes, often used in networking.

### 60. Explain caching strategies.
**Answer:** Techniques to store frequently accessed data locally to improve performance and reduce network requests.

---

## Memory Management

### 61. What is ARC (Automatic Reference Counting)?
**Answer:** Memory management system that automatically tracks and manages app's memory usage by keeping count of references to objects.

### 62. Explain retain cycles.
**Answer:** Situation where two or more objects hold strong references to each other, preventing deallocation and causing memory leaks.

### 63. How do you break retain cycles?
**Answer:** Use weak or unowned references to break the strong reference cycle.

```swift
class Parent {
    var child: Child?
}

class Child {
    weak var parent: Parent?
}
```

### 64. What is the difference between weak and unowned?
**Answer:**
- **weak:** Optional reference that becomes nil when object is deallocated
- **unowned:** Non-optional reference that assumes the object will never be nil

### 65. Explain memory leaks.
**Answer:** Situations where allocated memory is not released, causing the app to consume increasing amounts of memory over time.

### 66. What are strong reference cycles with closures?
**Answer:** When a closure captures self strongly and the object holds a strong reference to the closure.

```swift
class ViewController {
    lazy var closure: () -> Void = { [weak self] in
        self?.doSomething()
    }
}
```

### 67. What is the deinit method?
**Answer:** Method called immediately before a class instance is deallocated, used for cleanup tasks.

### 68. Explain autoreleasepool.
**Answer:** Mechanism for delaying the deallocation of objects until the pool is drained, managing temporary objects.

### 69. What are memory warnings?
**Answer:** System notifications sent when the device is running low on memory, requiring apps to free up memory.

### 70. How do you profile memory usage?
**Answer:** Use Instruments' Allocations and Leaks tools to monitor memory usage and detect memory issues.

---

## Concurrency & Threading

### 71. What is Grand Central Dispatch (GCD)?
**Answer:** Apple's technology for managing concurrent operations, providing a simple API for executing tasks on different queues.

```swift
DispatchQueue.main.async {
    // Update UI
}

DispatchQueue.global(qos: .background).async {
    // Background work
}
```

### 72. Explain the main queue.
**Answer:** Serial queue where all UI updates must be performed, running on the main thread.

### 73. What are Quality of Service (QoS) classes?
**Answer:** Categories that specify the priority and resource allocation for tasks:
- userInteractive
- userInitiated
- default
- utility
- background

### 74. Explain the difference between sync and async.
**Answer:**
- **sync:** Blocks current thread until task completes
- **async:** Doesn't block current thread, task executes concurrently

### 75. What is a race condition?
**Answer:** Bug that occurs when multiple threads access shared data simultaneously without proper synchronization.

### 76. Explain serial vs concurrent queues.
**Answer:**
- **Serial:** Executes tasks one at a time in FIFO order
- **Concurrent:** Can execute multiple tasks simultaneously

### 77. What is thread safety?
**Answer:** Property of code that can be safely executed by multiple threads simultaneously without causing data corruption.

### 78. Explain DispatchGroup.
**Answer:** Allows you to aggregate a set of tasks and synchronize behaviors on the group.

```swift
let group = DispatchGroup()
group.enter()
// Async task
group.leave()
group.notify(queue: .main) {
    // All tasks completed
}
```

### 79. What are barriers in GCD?
**Answer:** Synchronization points in concurrent queues that ensure exclusive access to shared resources.

### 80. Explain NSOperation and NSOperationQueue.
**Answer:** Higher-level abstraction over GCD providing more control over task execution, dependencies, and cancellation.

---

## Testing & Debugging

### 81. What is unit testing?
**Answer:** Testing individual components or functions in isolation to ensure they work correctly.

```swift
func testUserCreation() {
    let user = User(name: "John", age: 25)
    XCTAssertEqual(user.name, "John")
    XCTAssertEqual(user.age, 25)
}
```

### 82. Explain XCTest framework.
**Answer:** Apple's testing framework for unit tests, performance tests, and UI tests in iOS apps.

### 83. What is UI testing?
**Answer:** Testing the user interface and user interactions automatically using XCUITest framework.

### 84. Explain test-driven development (TDD).
**Answer:** Development approach where you write tests before implementing the actual code.

### 85. What are mocks and stubs?
**Answer:**
- **Mocks:** Objects that verify behavior and interactions
- **Stubs:** Objects that provide predetermined responses

### 86. How do you debug iOS apps?
**Answer:** Using Xcode debugger, breakpoints, LLDB commands, print statements, and Instruments.

### 87. What is the LLDB debugger?
**Answer:** Low-level debugger integrated into Xcode for debugging iOS applications.

### 88. Explain breakpoints.
**Answer:** Debugging aids that pause program execution at specific lines to examine state and variables.

### 89. What is exception breakpoint?
**Answer:** Special breakpoint that pauses execution when an exception is thrown, helping identify crash locations.

### 90. Explain the View Debugger.
**Answer:** Xcode tool that provides 3D visualization of your app's view hierarchy for debugging UI issues.

---

## Performance Optimization

### 91. How do you optimize table view performance?
**Answer:**
- Use cell reuse
- Implement lazy loading
- Optimize cell height calculations
- Use opaque views
- Avoid complex view hierarchies

### 92. What is lazy loading?
**Answer:** Technique of deferring initialization of resources until they're actually needed.

```swift
lazy var expensiveProperty: ExpensiveClass = {
    return ExpensiveClass()
}()
```

### 93. Explain image optimization techniques.
**Answer:**
- Use appropriate image formats
- Resize images to display size
- Use image caching
- Implement progressive loading

### 94. What is the main thread checker?
**Answer:** Runtime tool that detects UI updates performed on background threads.

### 95. How do you measure app performance?
**Answer:** Using Instruments tools like Time Profiler, Allocations, and Energy Log.

### 96. What is code profiling?
**Answer:** Analysis of program execution to identify performance bottlenecks and optimization opportunities.

### 97. Explain battery optimization.
**Answer:** Techniques to reduce power consumption:
- Minimize background processing
- Optimize network requests
- Use efficient algorithms
- Reduce CPU-intensive operations

### 98. What is the Time Profiler instrument?
**Answer:** Tool that samples your app's call stack at regular intervals to identify CPU-intensive code paths.

### 99. How do you optimize app launch time?
**Answer:**
- Minimize work in application:didFinishLaunching
- Use lazy initialization
- Optimize image loading
- Reduce dynamic library usage

### 100. Explain memory optimization techniques.
**Answer:**
- Use appropriate data structures
- Implement object pooling
- Release unused resources
- Optimize image memory usage

---

## App Store & Deployment

### 101. What is the app submission process?
**Answer:** Process of preparing, validating, and submitting your app to the App Store through App Store Connect.

### 102. Explain provisioning profiles.
**Answer:** Files that link signing certificates, app IDs, and device identifiers for app installation and distribution.

### 103. What are certificates in iOS development?
**Answer:** Digital certificates that verify developer identity and enable code signing for app distribution.

### 104. Explain the difference between development and distribution certificates.
**Answer:**
- **Development:** For testing on registered devices
- **Distribution:** For App Store submission or enterprise distribution

### 105. What is code signing?
**Answer:** Process of digitally signing your app to verify its authenticity and integrity.

### 106. Explain App Store Connect.
**Answer:** Web-based platform for managing apps, metadata, pricing, and analytics for the App Store.

### 107. What are app review guidelines?
**Answer:** Apple's standards that apps must meet to be approved for the App Store.

### 108. Explain TestFlight.
**Answer:** Apple's beta testing platform for distributing pre-release versions to internal and external testers.

### 109. What is app thinning?
**Answer:** Process where the App Store creates optimized versions of your app for specific devices.

### 110. Explain bitcode.
**Answer:** Intermediate representation of compiled programs that allows Apple to optimize apps without resubmission.

---

## Advanced iOS Concepts

### 111. What is SwiftUI?
**Answer:** Apple's declarative UI framework for building user interfaces across all Apple platforms.

```swift
struct ContentView: View {
    var body: some View {
        Text("Hello, World!")
            .padding()
    }
}
```

### 112. Explain Combine framework.
**Answer:** Apple's framework for handling asynchronous events by combining event-processing operators.

### 113. What is Core Animation?
**Answer:** Framework that provides high-performance animation capabilities for iOS apps.

### 114. Explain CALayer.
**Answer:** Object that manages image-based content and allows you to perform animations on that content.

### 115. What is Core Graphics?
**Answer:** 2D drawing API that provides low-level, lightweight 2D rendering with unmatched output fidelity.

### 116. Explain push notifications.
**Answer:** Messages sent from a server to iOS devices through Apple Push Notification service (APNs).

### 117. What is background app refresh?
**Answer:** Feature that allows apps to refresh their content in the background so it's ready when users open them.

### 118. Explain universal links.
**Answer:** Links that work seamlessly with your app, allowing users to open your app directly from Safari or other apps.

### 119. What is Core Location?
**Answer:** Framework that provides location and heading information to apps.

### 120. Explain app extensions.
**Answer:** Separate binaries that extend functionality of your app to other areas of the system.

---

## Security & Privacy

### 121. What is App Transport Security (ATS)?
**Answer:** Security feature that improves privacy and data integrity by requiring secure network connections.

### 122. Explain biometric authentication.
**Answer:** Using Touch ID or Face ID to authenticate users securely.

```swift
import LocalAuthentication

let context = LAContext()
context.evaluatePolicy(.biometryAny, localizedReason: "Authenticate") { success, error in
    // Handle result
}
```

### 123. What is certificate pinning?
**Answer:** Security technique that associates a host with their expected X.509 certificate or public key.

### 124. Explain data encryption in iOS.
**Answer:** Protecting sensitive data using encryption algorithms, both at rest and in transit.

### 125. What is the iOS Keychain?
**Answer:** Secure storage system for sensitive information like passwords, certificates, and cryptographic keys.

### 126. Explain app sandboxing.
**Answer:** Security mechanism that restricts app access to system resources and other apps' data.

### 127. What are privacy permissions?
**Answer:** User-granted permissions required to access sensitive device features like camera, location, contacts.

### 128. Explain code obfuscation.
**Answer:** Technique to make code difficult to understand while preserving functionality, used to protect intellectual property.

### 129. What is jailbreak detection?
**Answer:** Techniques to detect if a device has been jailbroken, which may compromise app security.

### 130. Explain secure coding practices.
**Answer:** Guidelines for writing code that is resistant to security vulnerabilities and attacks.

---

## Design Patterns & Best Practices

### 131. What is the Delegate pattern?
**Answer:** Design pattern that allows one object to send messages to another object when specific events happen.

```swift
protocol TableViewCellDelegate: AnyObject {
    func cellDidTapButton(_ cell: TableViewCell)
}

class TableViewCell: UITableViewCell {
    weak var delegate: TableViewCellDelegate?
    
    @IBAction func buttonTapped() {
        delegate?.cellDidTapButton(self)
    }
}
```

### 132. Explain the Command pattern.
**Answer:** Encapsulates a request as an object, allowing you to parameterize clients with different requests and queue operations.

### 133. What is the Builder pattern?
**Answer:** Creational pattern that constructs complex objects step by step.

```swift
class URLRequestBuilder {
    private var request = URLRequest(url: URL(string: "")!)
    
    func setURL(_ url: URL) -> URLRequestBuilder {
        request.url = url
        return self
    }
    
    func setMethod(_ method: String) -> URLRequestBuilder {
        request.httpMethod = method
        return self
    }
    
    func build() -> URLRequest {
        return request
    }
}
```

### 134. Explain the Adapter pattern.
**Answer:** Allows incompatible interfaces to work together by wrapping an existing class with a new interface.

### 135. What is the Strategy pattern?
**Answer:** Defines a family of algorithms, encapsulates each one, and makes them interchangeable.

### 136. Explain SOLID principles.
**Answer:**
- **S**ingle Responsibility Principle
- **O**pen/Closed Principle
- **L**iskov Substitution Principle
- **I**nterface Segregation Principle
- **D**ependency Inversion Principle

### 137. What is composition over inheritance?
**Answer:** Design principle that favors composing objects from other objects rather than inheriting from base classes.

### 138. Explain the Model-View-Presenter (MVP) pattern.
**Answer:** Architectural pattern where the Presenter handles all UI logic and acts as an intermediary between View and Model.

### 139. What is clean architecture?
**Answer:** Architecture that separates concerns into layers, making the codebase more maintainable and testable.

### 140. Explain dependency inversion.
**Answer:** High-level modules should not depend on low-level modules; both should depend on abstractions.

---

## Tools & Debugging

### 141. What is Instruments?
**Answer:** Performance analysis and testing tool for iOS apps, including profilers for memory, CPU, and network usage.

### 142. Explain the Allocations instrument.
**Answer:** Tool that tracks memory allocations and helps identify memory leaks and excessive memory usage.

### 143. What is the Leaks instrument?
**Answer:** Tool that detects memory leaks by identifying objects that are no longer reachable but haven't been deallocated.

### 144. Explain the Network instrument.
**Answer:** Tool that monitors network activity, helping optimize network usage and identify performance issues.

### 145. What is the Energy Log instrument?
**Answer:** Tool that measures your app's energy impact and identifies energy-intensive operations.

### 146. Explain Xcode's static analyzer.
**Answer:** Tool that analyzes code without running it to find potential bugs, memory leaks, and logic errors.

### 147. What is the View Hierarchy Debugger?
**Answer:** Tool that provides a 3D visualization of your app's view hierarchy for debugging layout issues.

### 148. Explain console logging.
**Answer:** Debugging technique using print statements or os_log to output information during app execution.

### 149. What is crash reporting?
**Answer:** System for collecting and analyzing app crashes to identify and fix issues.

### 150. Explain symbolication.
**Answer:** Process of converting memory addresses in crash reports to human-readable function names and line numbers.

---

## Frameworks & Libraries

### 151. What is Core Data vs SQLite?
**Answer:**
- **Core Data:** Object-relational mapping framework with automatic memory management
- **SQLite:** Lightweight SQL database engine requiring manual SQL queries

### 152. Explain MapKit framework.
**Answer:** Framework for embedding maps and location-based features into iOS apps.

### 153. What is AVFoundation?
**Answer:** Framework for working with audiovisual media, including playback, recording, and editing.

### 154. Explain Core Image framework.
**Answer:** Framework for image processing and analysis, providing filters and effects.

### 155. What is CloudKit?
**Answer:** Apple's cloud service framework for storing and synchronizing app data across devices.

### 156. Explain Metal framework.
**Answer:** Low-level graphics and compute API for high-performance rendering and parallel computation.

### 157. What is HealthKit?
**Answer:** Framework for accessing and sharing health and fitness data with user permission.

### 158. Explain HomeKit framework.
**Answer:** Framework for communicating with and controlling connected home automation accessories.

### 159. What is WatchKit?
**Answer:** Framework for developing Apple Watch apps and complications.

### 160. Explain ARKit framework.
**Answer:** Framework for creating augmented reality experiences using device cameras and motion sensors.

---

## Version Control & Collaboration

### 161. What is Git?
**Answer:** Distributed version control system for tracking changes in source code during software development.

### 162. Explain branching strategies.
**Answer:** Approaches for organizing code branches:
- Git Flow
- GitHub Flow
- Feature branching

### 163. What is a merge conflict?
**Answer:** Situation where Git cannot automatically merge changes from different branches, requiring manual resolution.

### 164. Explain pull requests.
**Answer:** Method for proposing changes to a codebase, allowing code review before merging.

### 165. What is continuous integration?
**Answer:** Practice of automatically building and testing code changes to detect issues early.

### 166. Explain code review process.
**Answer:** Systematic examination of code changes by peers to improve quality and share knowledge.

### 167. What is semantic versioning?
**Answer:** Versioning scheme using MAJOR.MINOR.PATCH format to communicate the nature of changes.

### 168. Explain Git hooks.
**Answer:** Scripts that run automatically at certain points in the Git workflow.

### 169. What is cherry-picking in Git?
**Answer:** Applying specific commits from one branch to another without merging entire branches.

### 170. Explain rebasing vs merging.
**Answer:**
- **Rebase:** Replays commits on top of another branch, creating linear history
- **Merge:** Combines branches while preserving branch history

---

## iOS Ecosystem & Trends

### 171. What is iOS 15's new features?
**Answer:** Features like Focus modes, Live Text, SharePlay, improved notifications, and enhanced privacy controls.

### 172. Explain App Clips.
**Answer:** Lightweight versions of apps that provide specific functionality without full app installation.

### 173. What is Sign in with Apple?
**Answer:** Authentication service that allows users to sign into apps using their Apple ID with privacy protection.

### 174. Explain iOS 14 widgets.
**Answer:** Home screen widgets that provide glanceable information and quick access to app functionality.

### 175. What is iOS App Privacy Labels?
**Answer:** Requirements to disclose what data your app collects and how it's used.

### 176. Explain iOS 14.5 App Tracking Transparency.
**Answer:** Framework requiring user permission before tracking across other companies' apps and websites.

### 177. What is iOS Universal Purchase?
**Answer:** Feature allowing users to buy an app once and use it across iPhone, iPad, Mac, Apple TV, and Apple Watch.

### 178. Explain iOS Shortcuts integration.
**Answer:** Framework allowing apps to expose functionality to Siri and the Shortcuts app.

### 179. What is iOS Screen Time API?
**Answer:** Framework for building parental controls and screen time management features.

### 180. Explain iOS 13 Dark Mode.
**Answer:** System-wide appearance option that apps can support for better user experience in low-light conditions.

---

## Final Advanced Topics

### 181. What is reactive programming?
**Answer:** Programming paradigm focused on asynchronous data streams and the propagation of change.

### 182. Explain functional programming concepts.
**Answer:** Programming paradigm emphasizing immutability, pure functions, and higher-order functions.

### 183. What is protocol-oriented programming vs OOP?
**Answer:** POP emphasizes protocols and value types over classes and inheritance, promoting composition.

### 184. Explain async/await in Swift.
**Answer:** Modern concurrency features for writing asynchronous code that looks and behaves like synchronous code.

### 185. What is actor model in Swift?
**Answer:** Reference type that protects its mutable state from data races by ensuring only one task can access it at a time.

### 186. Explain structured concurrency.
**Answer:** Approach to concurrent programming that ensures tasks have clear lifetimes and relationships.

### 187. What is property wrapper?
**Answer:** Feature that allows you to separate the code that manages how a property is stored from the code that defines a property.

### 188. Explain result builders.
**Answer:** Feature that allows building nested data structures in a natural, declarative way.

### 189. What is opaque return types?
**Answer:** Feature that allows a function to return a specific type without revealing what that type is.

### 190. Explain existential types.
**Answer:** Types that can hold any value that conforms to a particular protocol.

---

## Bonus Questions

### 191. How do you handle memory warnings?
**Answer:** Implement `didReceiveMemoryWarning`, release non-essential resources, and clear caches.

### 192. What is the difference between `viewDidLoad` and `viewWillAppear`?
**Answer:** `viewDidLoad` is called once when view loads, `viewWillAppear` is called every time view appears.

### 193. Explain KVO (Key-Value Observing).
**Answer:** Mechanism that allows objects to be notified of changes to specified properties of other objects.

### 194. What is method swizzling?
**Answer:** Technique for changing the implementation of existing methods at runtime.

### 195. Explain the difference between `copy` and `strong`.
**Answer:** `copy` creates a new instance, `strong` creates a strong reference to the existing instance.

### 196. What is toll-free bridging?
**Answer:** Feature that allows you to use Core Foundation and Foundation types interchangeably.

### 197. Explain the app lifecycle states.
**Answer:** Not Running, Inactive, Active, Background, Suspended.

### 198. What is the difference between `atomic` and `nonatomic`?
**Answer:** `atomic` provides thread safety for property access, `nonatomic` doesn't (default and recommended).

### 199. Explain categories vs extensions.
**Answer:** Categories (Objective-C) and extensions (Swift) both add functionality to existing classes without subclassing.

### 200. What are your strategies for staying updated with iOS development?
**Answer:** Following Apple developer documentation, WWDC sessions, iOS dev communities, blogs, and experimenting with new features.

---

## Conclusion

This comprehensive list covers the essential topics for an iOS developer with 3.2 years of experience. The questions range from fundamental concepts to advanced topics, ensuring thorough preparation for technical interviews. Remember to practice implementing these concepts in actual code and stay updated with the latest iOS developments.

**Key Areas to Focus On:**
- Swift language fundamentals and best practices
- iOS app architecture patterns
- UIKit and interface development
- Memory management and performance optimization
- Networking and data persistence
- Testing and debugging techniques
- Current iOS features and frameworks

Good luck with your iOS developer interviews!