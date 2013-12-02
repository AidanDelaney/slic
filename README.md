# SLIC - SPARQL Command Line Interface

Apache jena.arq wasn't playing ball, so I hacked this up.  It's also useful to be able to step through a ResultSet

## Building

'''bash
$ mvn compile
'''

## Testing

The location of the resource files ((.owl, .sparql) pairs) needs to be defined as a property.

'''bash
$ mvn test -D"slic.test.resources=`pwd`/src/test/resources/"
'''