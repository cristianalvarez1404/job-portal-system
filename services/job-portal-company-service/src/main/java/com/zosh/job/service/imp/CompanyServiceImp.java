package com.zosh.job.service.imp;

import com.zosh.job.domain.CompanyStatus;
import com.zosh.job.domain.CompanyType;
import com.zosh.job.domain.IndustryType;
import com.zosh.job.dto.CompanyRequest;
import com.zosh.job.dto.CompanyResponse;
import com.zosh.job.dto.SocialLinkResponse;
import com.zosh.job.mapper.CompanyMapper;
import com.zosh.job.modal.Company;
import com.zosh.job.modal.SocialLink;
import com.zosh.job.repository.CompanyRepository;
import com.zosh.job.service.CompanyService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImp implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public CompanyResponse createCompany(Long ownerId, CompanyRequest req) throws Exception {
        if(companyRepository.existsByOwnerId(ownerId)){
            throw new Exception("You already have a company registered. Only one company per account is allowed.");
        }
        if(companyRepository.existsByName(req.getName())){
            throw new Exception("Company already exists. Please choose a different name.");
        }
        if(req.getRegistrationNumber() != null && companyRepository.existsByRegistrationNumber(req.getRegistrationNumber())){
            throw new Exception("Company already exists. Please choose a different registration number.");
        }
        String slug = generateUniqueSlug(req.getName());

        Company company = Company
                .builder()
                .name(req.getName()).slug(slug)
                .tagline(req.getTagLine())
                .description(req.getDescriptor())
                .logoUrl(req.getLogoUrl())
                .coverImageUrl(req.getConvertImageUrl())
                .website(req.getWebsite())
                .phone(req.getPhone())
                .email(req.getEmail())
                .foundedYear(req.getFoundedYear())
                .companySize(req.getCompanySize())
                .companyType(req.getCompanyType())
                .industryType(req.getIndustryType())
                .registrationNumber(req.getRegistrationNumber())
                .ownerId(ownerId)
                .socialLinks(mapSocialLinks(req.getSocialLinks()))
                .build();

        Company saved = companyRepository.save(company);

        return CompanyMapper.toResponse(saved);
    }

    private List<SocialLink> mapSocialLinks(List<SocialLinkResponse> socialLinks) {
        if(socialLinks == null || socialLinks.isEmpty()){
            return new ArrayList<>();
        }
        return socialLinks.stream()
                .map(e->SocialLink.builder()
                        .platform(e.getPlatform())
                        .url(e.getUrl())
                        .build())
                .collect(Collectors.toList())
                ;
    }

    private String generateUniqueSlug( String name) {
        String base = name.toLowerCase()
                .replaceAll("[a-z0-9\\s-]", "")
                .trim().replaceAll("[\\s-]","-");
        if(!companyRepository.existsBySlug(base)){
            return base;
        }

        int counter = 1;
        while(companyRepository.existsBySlug(base+"-"+counter){
            counter++;
        }
        return base+"-"+counter;
    }

    @Override
    public CompanyResponse getCompanyById(Long id) throws Exception {
        Company company = companyRepository.findById(id).orElseThrow(() -> new Exception("Company not found with id"));
        return CompanyMapper.toResponse(company);
    }

    @Override
    public CompanyResponse getMyCompany(Long ownerId) throws Exception {
        Company company = companyRepository.findByOwnerId(ownerId)
                .orElseThrow(() -> new Exception("Company not exists for owner "+ ownerId));

        return CompanyMapper.toResponse(company);
    }

    @Override
    public List<CompanyResponse> getAllCompanies(CompanyType companyType, IndustryType industryType, CompanyStatus companyStatus) {
        return List.of();
    }

    @Override
    public CompanyResponse updateCompany(Long companyId, Long ownerId, CompanyRequest req) {
        return null;
    }

    @Override
    public CompanyResponse verifyCompany(Long companyId) {
        return null;
    }

    @Override
    public void deleteCompany(Long companyId) {

    }

    @Override
    public CompanyResponse deactivateCompany(Long companyId) {
        return null;
    }

    @Override
    public Company getCompanyEntityById(Long id) {
        return null;
    }
}
