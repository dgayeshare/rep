package org.creationpartenaire.repository;


import org.creationpartenaire.entity.OnboardComByPartner;
import org.creationpartenaire.entity.PartnerOnboarded;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OnboardComByPartnerRepository extends JpaRepository<OnboardComByPartner, Long> {
    List<OnboardComByPartner> findByPartner(PartnerOnboarded partner);
}