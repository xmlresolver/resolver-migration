# Migration from the Apache Commons Resolver to XMLResolver

One of the first things I remember working on at Sun Microsystems was an
[XML catalog resolver](https://en.wikipedia.org/wiki/XML_catalog).
It was distributed through Java.net in the dim dark past and as part
of the [Apache xml-commons](http://xerces.apache.org/xml-commons/)
project sometime after that. It’s rather long in the tooth these
days and uses some design patterns I wouldn’t now recommend.

The [xmlresolver](https://github.com/ndw/xmlresolver) project is a
reimplementation of the catalog resolver in a more modern design and
with some new features, including the ability to automatically cache
resources.

This repository is a response to [issue #30](https://github.com/ndw/xmlresolver/issues/30) in the
[xmlresolver](https://github.com/ndw/xmlresolver) repository which asks “how do I migrate
from the old resolver to the new one?”

I’m pleased to say, it’s straightforward.

## Using the class names

If you’re using the resolver by passing the name of the class as a
system property or as an argument to another application (`-x`, `-y`,
or `-r` to Saxon, `-URIRESOLVER` to Xalan, etc.), just use the new
class name:

<table>
<thead>
<tr>
<th>Instead of</th>
<th>Use</th>
</tr>
</thead>
<tbody>
<tr>
<td><code>org.apache.xml.resolver.tools.ResolvingXMLReader</code></td>
<td><code>org.xmlresolver.tools.ResolvingXMLReader</code></td>
</tr>
<tr>
<td><code>org.apache.xml.resolver.tools.CatalogResolver</code></td>
<td><code>org.xmlresolver.Resolver</code></td>
</tr>
</tbody>
</table>

## In your own application

This repository has two example applications that do the same thing. The application
in the `apache` directory uses the old Apache Commons resolver. The application in
the `xmlresolver` directory uses the new XML Resolver library.

The differences are small. As with the class names, for a simple application, the difference
is just

```
import org.xmlresolver.tools.ResolvingXMLReader;
```

instead of

```
import org.apache.xml.resolver.tools.ResolvingXMLReader;
```

If you have an application that uses the old library in a different
way, and you can’t figure out how to convert it, [open an issue](https://github.com/ndw/resolver-migration/issues)
here with a small sample that demonstrates how you’re using the old library.
I’ll see if I can’t make another example application that shows the before-and-after implementations.

## Configuration

There are slight differences in how the libraries are configured. The XML Resolver is backwards compatible
with the old resolver, but you may wish to [read the description](https://xmlresolver.org/) of the
new resolver and migrate your `CatalogManager.properties` file to `XMLResolver.properties`.
