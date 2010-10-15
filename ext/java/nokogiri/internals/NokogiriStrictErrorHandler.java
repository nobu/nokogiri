package nokogiri.internals;

import org.apache.xerces.xni.parser.XMLParseException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Strict error handler. Even though strict is specified, Nokogiri allows to go further
 * when NOERROR or/both NOWARNING is/are true.
 * 
 * @author sergio
 */
public class NokogiriStrictErrorHandler extends NokogiriErrorHandler {
    public NokogiriStrictErrorHandler(boolean noerror, boolean nowarning) {
        super(noerror, nowarning);
    }

    public void warning(SAXParseException spex) throws SAXException {
        if (!nowarning) throw spex;
        else errors.add(spex);
    }

    public void error(SAXParseException spex) throws SAXException {
        if (!noerror) throw spex;
        else errors.add(spex);
    }

    public void fatalError(SAXParseException spex) throws SAXException {
        throw spex;
    }

    public void error(String domain, String key, XMLParseException e) throws XMLParseException {
        if (!noerror) throw e;
        else errors.add(e);
    }

    public void fatalError(String domain, String key, XMLParseException e) throws XMLParseException {
        throw e;
    }

    public void warning(String domain, String key, XMLParseException e) throws XMLParseException {
        if (!nowarning) throw e;
        if (!usesNekoHtml(domain)) errors.add(e);
    }

}
