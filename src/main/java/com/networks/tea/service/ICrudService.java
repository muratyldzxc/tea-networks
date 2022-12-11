package com.networks.tea.service;

import java.util.Optional;

public interface ICrudService<T, S> {
    S add(S entity);

    S findById(T id);

    S update(S entity);

    void deleteById(T id);
}
