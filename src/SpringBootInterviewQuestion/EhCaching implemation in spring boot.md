# EhCaching implemation in spring boot

```text
In our project we are using Ehcache - we used it to cache frequently accessed data and improve performance
 by reducing redundant database calls.
step1- we define cache setting in ehcache.xml where we define
 <defaultCache   maxEntriesLocalHeap="1000" // max size
                  timeToLiveSeconds="600"    // kab tk valid hoga
                  eternal="false"  -- it will expire after timeToLiveSeconds jo time hame diya.
             memoryStoreEvictionPolicy="LRU" />  // remove least recently used entries when max size is reached.

				  Example-  <cache name="productCache"
                       maxEntriesLocalHeap="500"
                       timeToLiveSeconds="300"
                      eternal="false"
                      memoryStoreEvictionPolicy="LRU" />

step 2- Add requried dependecny
        spring-boot-starter-cache
         ehcache

step 3 - @SpringBootApplication
         @EnableCaching
		 in main class

step 4- apply caching to the method

        @Service
    public class ProductService {

    @Cacheable(value = "productCache", key = "#productId")
    public Product getProductById(Long productId) {
        // This result will be cached
        return productRepository.findById(productId).orElse(null);
    }

    @CacheEvict(value = "productCache", key = "#productId")
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}

step 5 - the data store in heap memory

---------------------------------------------------------------------
🔁 What Happens on Startup (Step-by-Step)
✅ Spring Boot scans ehcache.xml from resources/ folder.

✅ Beans like EhCacheManagerFactoryBean or auto-configured CacheManager are initialized.

✅ All <cache> definitions are registered with the cache manager.

✅ If you use @EnableCaching, Spring sets up AOP proxies to intercept @Cacheable methods.

✅ When you call a cached method, Ehcache checks its in-memory store (like a map).

✅ If no entry is found → method runs → result stored in cache.

-----------------------------------------------
When the application starts, Spring Boot auto-configures Ehcache if it detects ehcache.xml in the classpath.
It creates an EhCacheCacheManager and registers all caches defined in the XML — for example, productCache,
partMasterCache, etc."

"These caches are stored in Java heap memory by default. As soon as a method annotated with @Cacheable is
called, Spring checks if the result is already in the cache — if not, it runs the method and stores the
result in the corresponding cache."

"The actual cache data is stored in-memory inside the JVM unless we explicitly configure off-heap or disk
storage. Since Ehcache is embedded, it doesn't use any external process unless we enable disk persistence
 or clustering."
```
