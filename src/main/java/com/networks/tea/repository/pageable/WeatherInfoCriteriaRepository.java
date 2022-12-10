package com.networks.tea.repository.pageable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.networks.tea.model.WeatherInfo;
import com.networks.tea.repository.pageable.filter.WeatherInfoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherInfoCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public WeatherInfoCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public List<WeatherInfo> findAllWithPageableFilters(WeatherInfoFilter filters, Pageable paging){
        CriteriaQuery<WeatherInfo> criteriaQuery = criteriaBuilder.createQuery(WeatherInfo.class);
        Root<WeatherInfo> weatherInfoRoot = criteriaQuery.from(WeatherInfo.class);

        Predicate finalPredicate = getPredicate(filters, weatherInfoRoot);

        criteriaQuery.where(finalPredicate);

        TypedQuery<WeatherInfo> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(paging.getPageNumber() * paging.getPageSize());
        typedQuery.setMaxResults(paging.getPageSize());

        return typedQuery.getResultList();
    }



    private Predicate getPredicate(WeatherInfoFilter filters,
                                   Root<WeatherInfo> weatherInfoRoot) {
        List<Predicate> predicates = new ArrayList<>();

        if(Objects.nonNull(filters.getLocation())){
            predicates.add(
                    criteriaBuilder.equal(weatherInfoRoot.get("location"), filters.getLocation() )
            );
        }

        if(Objects.nonNull(filters.getCondition())){
            predicates.add(
                    criteriaBuilder.equal(weatherInfoRoot.get("conditionOfWeather"), filters.getCondition())
            );
        }

        if(Objects.nonNull(filters.getStartDate()) && Objects.nonNull(filters.getEndDate())){
            predicates.add(
                    criteriaBuilder.between(weatherInfoRoot.get("timestampOfWeather"), filters.getStartDate(), filters.getEndDate())
            );
        }

        if(Objects.nonNull(filters.getTemperature())){
            predicates.add(
                    criteriaBuilder.equal(weatherInfoRoot.get("temperature"), filters.getTemperature())
            );
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
