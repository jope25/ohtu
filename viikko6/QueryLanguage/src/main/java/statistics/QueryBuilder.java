
package statistics;

import statistics.matcher.*;

public class QueryBuilder {

    Matcher matcher;
    
    public QueryBuilder() {
        matcher = new And();
    }
    
    QueryBuilder hasAtLeast(int value, String category) {
        matcher = new And(matcher, new HasAtLeast(value, category));
        return this;
    }
    
    QueryBuilder hasFewerThan(int value, String category) {
        matcher = new And(matcher, new HasFewerThan(value, category));
        return this;
    }
    
    QueryBuilder playsIn(String team) {
        matcher = new And(matcher, new PlaysIn(team));
        return this;
    }
    
    QueryBuilder oneOf(Matcher... matchers) {
        matcher = new Or(matchers);
        return this;
    }
    
    QueryBuilder not(Matcher matcher) {
        this.matcher = new Not(matcher);
        return this;
    }
    
    public Matcher build() {
        Matcher help = matcher;
        matcher = new And();
        return help;
    }
}
