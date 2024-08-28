Micro service communication using eureka , rest template.  Only Get Operation handled sofar.
1. run application servicediscovery server
2. run application movie info
3. run applicatin movie rating
4. run appliation movie catalog
   movie catalog will discover the REST API url using service discovery, usring rest template,  @EnableEurekaServer , @EnableDiscoveryClient need to these two annotations in server and client classess
   
