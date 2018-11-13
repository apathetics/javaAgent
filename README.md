# Java Spring HTTP Request/Response Metric Filter [![Build Status](https://travis-ci.com/apathetics/javaAgent.svg?branch=master)](https://travis-ci.com/apathetics/javaAgent)
This is a Spring Boot web application that leverages Spring's Filters to create a custom filter "extension" 
that collects and logs HTTP request/response metadata such as request time and response size.

This includes a custom Spring Filter, Filter configuration, a Metric model, and a Metric "dao" that uses an in-memory
ConcurrentHashMap in order to calculate collected metrics to display minimum/maximum/average of request time (ms) and 
response size (bytes).
<br>

## Purpose (Why I built it)
With the goal of creating a metric-gathering "extension" for a Java web application in mind, I set out to do some research
on the best way to do this.

The best way to genericize this extension would be to create it as an Java instrumentation agent which would let it be
usable for almost any Java application.

Many modern applications based on Java use a Spring MVC or Spring Boot framework in industry, so I decided that a
good compromise would be to write a modular component for Spring that would achieve my goal. The interaction
between servlets and filters seemed like a natural place to start investigating because HTTP requests/responses can
always be processed through a filter.

So, I decided to base my project around creating my own custom filter class that would be modular and decoupled
from my own test web application. Any user interested in using the filter should be able to simply pluck the 
RequestResponseFilter class, the sample Metric model, the in-memory "DAO", and the sample FilterConfig to quickly import
and extend into their own Spring project.
<br>

## How To Use
To clone and run this application, you will need [Git](https://git-scm.com) and [Maven](https://maven.apache.org)
installed on your computer.

From your command line:
```bash
# Clone this repository
$ git clone https://github.com/apathetics/javaAgent.git

# Navigate to directory
$ cd javaAgent

# Install dependencies using Maven
$ mvn install

# Run the application
$ mvn spring-boot:run

```

If you are starting from an IDE, then you can run the AgentsMetricsApplication from the IDE instead.
<br>

## Examples of Usage
The home screen where collected metrics are displayed in a table.
<br>
Note that upon entering the home page, it has already collected responses for loading .js, .css, and more.
<img src="https://i.gyazo.com/913d3ddda17663a169c34df78d597ad1.png">

<br>
For the search page, simply look in your logs for the unique id generated for the request/response you're interested in.
<img src="https://i.gyazo.com/231a9b2c5e56b6fb66afe8eb15a1d902.png">

<br>
Then, you can input the ID to retrieve the request time and response size for that particular request.
<img src="https://i.gyazo.com/f087b5251da5558de191eee34f771750.png">
<br>

## Goals for the Future

- See how to further decouple this from the web application by creating a Java instrumentation agent.
- Customize data structures for scalability and optimization.
- Write integration tests.
- Research a more thorough manner of unit testing servlet/filter interaction.

<br>

##Feedback
I hope you've been able to learn a little bit more about Spring and the possible usage of filters for creating
metric collection and logging.

I am always happy to receive feedback and suggestions on how to improve as an engineer, so please don't hesitate to
send me an email or leave an issue.

Thank you for stopping by!

