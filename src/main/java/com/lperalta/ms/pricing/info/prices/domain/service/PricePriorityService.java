package com.lperalta.ms.pricing.info.prices.domain.service;

import com.lperalta.ms.pricing.info.prices.application.exception.NotDataFoundException;
import com.lperalta.ms.pricing.info.prices.application.exception.PriceConfigurationErrorException;
import com.lperalta.ms.pricing.info.prices.domain.model.PriceQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PricePriorityService {

    public PriceQuery getHighestPriorityPrice(List<PriceQuery> prices)
            throws PriceConfigurationErrorException, NotDataFoundException {
        List<Integer> indicesOfMaxPriorities = this.maxIndices(prices.stream().map(PriceQuery::getPriority).toList());

        if (indicesOfMaxPriorities.size() > 1) {
            throw new PriceConfigurationErrorException();
        }

        return prices.get(indicesOfMaxPriorities.get(0));
    }

    private List<Integer> maxIndices(List<Integer> list) throws NotDataFoundException {
        int max = list.stream().mapToInt(Integer::intValue).max().orElseThrow(NotDataFoundException::new);

        return IntStream.range(0, list.size())
                .filter(i -> list.get(i) == max)
                .boxed()
                .collect(Collectors.toList());
    }
}