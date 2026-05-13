package jpa.dao.generic;

import jpa.domain.Magazine;
import jpa.domain.User;

public class MagazineDao extends AbstractJpaDao<Long, Magazine>{
    public MagazineDao() {
        super(Magazine.class);
    }

}
