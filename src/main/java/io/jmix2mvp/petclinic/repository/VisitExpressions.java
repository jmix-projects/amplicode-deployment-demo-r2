package io.jmix2mvp.petclinic.repository;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Visitor;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import io.jmix2mvp.petclinic.Authorities;
import io.jmix2mvp.petclinic.entity.QVisit;
import io.jmix2mvp.petclinic.entity.Visit;
import io.jmix2mvp.petclinic.entity.VisitState;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;

public interface VisitExpressions {
    static BooleanExpression byState(VisitState state) {
        return QVisit.visit.state.eq(state);
    }

    static BooleanExpression byDatesRange(LocalDateTime from, LocalDateTime to) {
        return QVisit.visit.visitStart.loe(to).and(QVisit.visit.visitStart.goe(from));
    }

    static BooleanExpression withRowLevelPermissions(BooleanExpression expression, Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean isVet = authorities.stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(Authorities.VETERINARIAN));
        if (isVet) {
            return expression.and(QVisit.visit.veterinarian.user.username.eq(authentication.getName()));
        }

        boolean isOwner = authorities.stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(Authorities.OWNER));
        if(isOwner) {
            return expression.and(QVisit.visit.pet.owner.isNotNull().and(QVisit.visit.pet.owner.user.username.eq(authentication.getName())));
        }

        return expression;
    }
}
