package jpa.dao.generic;

import jpa.domain.Element;

public class ElementDao extends AbstractJpaDao<Long,Element>  {
    public ElementDao() {
        super(Element.class);
    }
}
