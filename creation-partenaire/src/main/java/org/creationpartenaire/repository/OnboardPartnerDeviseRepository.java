package org.creationpartenaire.repository;


import org.creationpartenaire.entity.OnboardPartnerDevise;
import org.creationpartenaire.entity.PartnerOnboarded;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OnboardPartnerDeviseRepository extends JpaRepository<OnboardPartnerDevise, Long> {
    List<OnboardPartnerDevise> findByPartner(PartnerOnboarded partner);
}