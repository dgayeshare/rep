package org.creationpartenaire.repository;

import org.creationpartenaire.entity.PartnerOnboarded;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PartnerOnboardedRepository extends JpaRepository<PartnerOnboarded, Long> {
    boolean existsByEmailResp(String email);
}