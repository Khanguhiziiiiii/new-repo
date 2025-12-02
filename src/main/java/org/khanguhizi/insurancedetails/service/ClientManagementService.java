package org.khanguhizi.insurancedetails.service;

import lombok.*;
import org.khanguhizi.insurancedetails.dto.*;
import org.khanguhizi.insurancedetails.exceptions.*;
import org.khanguhizi.insurancedetails.models.*;
import org.khanguhizi.insurancedetails.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientManagementService {
    private ClientManagementRepository clientManagementRepository;

    public ApiResponse createClient(ClientDetailsRequest createClientsRequest) {
        //Check if the customer exists
        Optional<ClientDetails> emailCheck = clientManagementRepository.findByEmail(createClientsRequest.getEmail());
        if (emailCheck.isPresent()) {
            throw new DuplicateCredentialsException("Client with email " + createClientsRequest.getEmail() + " already exists");
        }
        Optional<ClientDetails> idCheck = clientManagementRepository.findByIdNo(createClientsRequest.getIdNo());
        if (idCheck.isPresent()) {
            throw new DuplicateCredentialsException("Client with id " + createClientsRequest.getIdNo() + " already exists");
        }
        Optional<ClientDetails> kraCheck = clientManagementRepository.findByKraPin(createClientsRequest.getKraPin());
        if (kraCheck.isPresent()) {
            throw new DuplicateCredentialsException("Client with KRA pin " + createClientsRequest.getKraPin() + " already exists");
        }

        ClientDetails clientDetails = ClientDetails.builder()
                .policyHolder(createClientsRequest.getPolicyHolder())
                .mobile(createClientsRequest.getMobile())
                .tel(createClientsRequest.getTel())
                .email(createClientsRequest.getEmail())
                .idNo(createClientsRequest.getIdNo())
                .kraPin(createClientsRequest.getKraPin())
                .postalAddress(createClientsRequest.getPostalAddress())
                .postalCode(createClientsRequest.getPostalCode())
                .bankAccNo(createClientsRequest.getBankAccNo())
                .build();
        clientManagementRepository.save(clientDetails);

        ClientDetailsResponse createClientResponse = new ClientDetailsResponse();
        createClientResponse.setPolicyHolder(clientDetails.getPolicyHolder());
        createClientResponse.setMobile(clientDetails.getMobile());
        createClientResponse.setTel(clientDetails.getTel());
        createClientResponse.setEmail(clientDetails.getEmail());
        createClientResponse.setIdNo(clientDetails.getIdNo());
        createClientResponse.setKraPin(clientDetails.getKraPin());
        createClientResponse.setPostalAddress(clientDetails.getPostalAddress());
        createClientResponse.setPostalCode(clientDetails.getPostalCode());
        createClientResponse.setBankAccNo(clientDetails.getBankAccNo());

        return ApiResponse.builder()
                .message("Client registered successfully!")
                .data(createClientResponse)
                .status(String.valueOf(HttpStatus.CREATED.value()))
                .build();
    }

    public ApiResponse fetchAllClientsDetails(){
        List<ClientDetails> clientDetailsList = clientManagementRepository.findAll();

        if (clientDetailsList.isEmpty()) {
            throw new NoRecordsFoundException("No Clients Found!");
        }

        List<ClientDetailsResponse> allClientsDetails = clientDetailsList.stream()
                .map(clientDetails -> {
                    ClientDetailsResponse fetchAllClientDetailsResponse = new ClientDetailsResponse();
                    fetchAllClientDetailsResponse.setPolicyHolder(fetchAllClientDetailsResponse.getPolicyHolder());
                    fetchAllClientDetailsResponse.setMobile(fetchAllClientDetailsResponse.getMobile());
                    fetchAllClientDetailsResponse.setTel(fetchAllClientDetailsResponse.getTel());
                    fetchAllClientDetailsResponse.setEmail(fetchAllClientDetailsResponse.getEmail());
                    fetchAllClientDetailsResponse.setIdNo(fetchAllClientDetailsResponse.getIdNo());
                    fetchAllClientDetailsResponse.setKraPin(fetchAllClientDetailsResponse.getKraPin());
                    fetchAllClientDetailsResponse.setPostalAddress(fetchAllClientDetailsResponse.getPostalAddress());
                    fetchAllClientDetailsResponse.setPostalCode(fetchAllClientDetailsResponse.getPostalCode());
                    fetchAllClientDetailsResponse.setBankAccNo(fetchAllClientDetailsResponse.getBankAccNo());
                    return fetchAllClientDetailsResponse;
                })
                .toList();

        return ApiResponse.builder()
                .message("Clients Fetched Successfully!")
                .data(allClientsDetails)
                .status(String.valueOf(HttpStatus.OK.value()))
                .build();
    }

    public ApiResponse updateClientDetails(ClientDetailsRequest updateClientDetails){
        Optional<ClientDetails> idCheck = clientManagementRepository.findByIdNo(updateClientDetails.getIdNo());
        if (idCheck.isEmpty()) {
            throw new NoRecordsFoundException("Client with id " + updateClientDetails.getIdNo() + " does not exist");
        }

        ClientDetails clientDetails =  idCheck.get();

        clientDetails.setPolicyHolder(updateClientDetails.getPolicyHolder());
        clientDetails.setMobile(updateClientDetails.getMobile());
        clientDetails.setTel(updateClientDetails.getTel());
        clientDetails.setEmail(updateClientDetails.getEmail());
        clientDetails.setIdNo(updateClientDetails.getIdNo());
        clientDetails.setKraPin(updateClientDetails.getKraPin());
        clientDetails.setPostalAddress(updateClientDetails.getPostalAddress());
        clientDetails.setPostalCode(updateClientDetails.getPostalCode());
        clientDetails.setBankAccNo(updateClientDetails.getBankAccNo());
        clientManagementRepository.save(clientDetails);

        ClientDetailsResponse updateClientResponse = new ClientDetailsResponse();
        updateClientResponse.setPolicyHolder(clientDetails.getPolicyHolder());
        updateClientResponse.setMobile(clientDetails.getMobile());
        updateClientResponse.setTel(clientDetails.getTel());
        updateClientResponse.setEmail(clientDetails.getEmail());
        updateClientResponse.setIdNo(clientDetails.getIdNo());
        updateClientResponse.setKraPin(clientDetails.getKraPin());
        updateClientResponse.setPostalAddress(clientDetails.getPostalAddress());
        updateClientResponse.setPostalCode(clientDetails.getPostalCode());
        updateClientResponse.setBankAccNo(clientDetails.getBankAccNo());

        return ApiResponse.builder()
                .message("Client Updated Successfully!")
                .data(updateClientResponse)
                .status(String.valueOf(HttpStatus.OK.value()))
                .build();

    }

    public ApiResponse fetchClientDetailsByIdNo(String idNo){
        Optional<ClientDetails> existingClientDetails = clientManagementRepository.findByIdNo(idNo);
        if(existingClientDetails.isEmpty()){
            throw new NoRecordsFoundException("Invalid Client ID Number: " + idNo);
        }

        ClientDetails clientDetails = existingClientDetails.get();

        ClientDetailsResponse fetchClientByIdNoResponse = new ClientDetailsResponse();
        fetchClientByIdNoResponse.setPolicyHolder(clientDetails.getPolicyHolder());
        fetchClientByIdNoResponse.setMobile(clientDetails.getMobile());
        fetchClientByIdNoResponse.setTel(clientDetails.getTel());
        fetchClientByIdNoResponse.setEmail(clientDetails.getEmail());
        fetchClientByIdNoResponse.setIdNo(clientDetails.getIdNo());
        fetchClientByIdNoResponse.setKraPin(clientDetails.getKraPin());
        fetchClientByIdNoResponse.setPostalAddress(clientDetails.getPostalAddress());
        fetchClientByIdNoResponse.setPostalCode(clientDetails.getPostalCode());
        fetchClientByIdNoResponse.setBankAccNo(clientDetails.getBankAccNo());

        return ApiResponse.builder()
                .message("Client Fetched Successfully!")
                .data(fetchClientByIdNoResponse)
                .status(String.valueOf(HttpStatus.OK.value()))
                .build();
    }
}
