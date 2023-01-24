package nl.novi.homeprojects.services;

import nl.novi.homeprojects.dtos.input.ClientInputDto;
import nl.novi.homeprojects.dtos.output.ClientOutputDto;
import nl.novi.homeprojects.exceptions.RecordNotFoundException;
import nl.novi.homeprojects.models.*;
import nl.novi.homeprojects.repositorys.*;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Optional;

@Service
public class ClientService {

    public static ClientRepository clientRepository;
    public static AssignmentRepository assignmentRepository;
    public static UserRepository userRepository;
    public static DocFileRepository docFileRepository;
    private static ExecutorRepository executorRepository;

    public ClientService (ClientRepository clientRepository, AssignmentRepository assignmentRepository, UserRepository userRepository, ExecutorRepository executorRepository, DocFileRepository docFileRepository) {
        this.clientRepository = clientRepository;
        this.assignmentRepository = assignmentRepository;
        this.userRepository = userRepository;
        this.executorRepository = executorRepository;
        this.docFileRepository = docFileRepository;
    }


    public String createClient(ClientInputDto clientInputDto) {
        Client newClient = new Client();
        Executor executor = new Executor();

        newClient.setUsername(clientInputDto.getUsername());
        newClient.setFirstname(clientInputDto.getFirstname());
        newClient.setLastname(clientInputDto.getLastname());
        newClient.setMobile(clientInputDto.getMobile());
        newClient.setAdres(clientInputDto.getAdres());
        newClient.setPlace(clientInputDto.getPlace());
        newClient.setZipcode(clientInputDto.getZipcode());
        newClient.setEmail(clientInputDto.getEmail());
        newClient.setStory(clientInputDto.getStory());
        Client savedClient = clientRepository.save(newClient);

        executor.setName(clientInputDto.getUsername());
        Executor savedExecutor= executorRepository.save(executor);
        
        assignExecutorToClient(newClient.getUsername(), executor.getName());

        return savedClient.getUsername() + savedExecutor.getName();
    }


    public Iterable<ClientOutputDto> getClients() {
        ArrayList<ClientOutputDto> clientsList = new ArrayList<>();

        Iterable<Client> allClients = clientRepository.findAll();
        for (Client client : allClients) {
            clientsList.add(transferToClientDto(client));
        }
        return clientsList;
    }


    public ClientOutputDto getOneClient(String id) {
        Optional<Client> client = clientRepository.findById(id);

        if (client.isEmpty()) {
            throw new RecordNotFoundException("No Client found with id: " + id);

        } else {
            return transferToClientDto(client.get());
        }
    }


    public String deleteClient(String id) {
        Optional<Client> deleteClient = clientRepository.findById(id);

        if (deleteClient.isEmpty()) {
            throw new RecordNotFoundException("No client found with id: " + id);
        } else {
            clientRepository.deleteById(id);
            return "Client with id: " + id + " is deleted!";
        }
    }


    public ClientOutputDto patchClient(String id, ClientInputDto clientInputDto) {
        Optional<Client> optionalClient = clientRepository.findById(id);

        if (optionalClient.isPresent()) {
            Client clientUpdate = optionalClient.get();

            if (clientInputDto.getFirstname() != null) {
                clientUpdate.setFirstname(clientInputDto.getFirstname());
            }
            if (clientInputDto.getLastname() != null) {
                clientUpdate.setLastname(clientInputDto.getLastname());
            }
            if (clientInputDto.getMobile() != null) {
                clientUpdate.setMobile(clientInputDto.getMobile());
            }
            if (clientInputDto.getAdres() != null) {
                clientUpdate.setAdres(clientInputDto.getAdres());
            }
            if (clientInputDto.getPlace() != null) {
                clientUpdate.setPlace(clientInputDto.getPlace());
            }
            if (clientInputDto.getZipcode() != null) {
                clientUpdate.setZipcode(clientInputDto.getZipcode());
            }
            if (clientInputDto.getEmail() != null) {
                clientUpdate.setEmail(clientInputDto.getEmail());
            }
            if (clientInputDto.getStory() != null) {
                clientUpdate.setStory(clientInputDto.getStory());
            }

            Client updatedClient = clientRepository.save(clientUpdate);
            return transferToClientDto(updatedClient);
        } else {
            throw new RecordNotFoundException("No client found with id: " + id);
        }
    }


    public static void assignAssignmentToClient(String id, String assignmentId) {

        Optional<Client> optionalClient = clientRepository.findById(id);
        Optional<Assignment> optionalAssignment = assignmentRepository.findById(assignmentId);

        if (optionalAssignment.isEmpty()) {
            throw new RecordNotFoundException("Assignment with id: " + assignmentId + " not found");
        } else if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Client with username id: " + id + " not found");

        } else {
            Client client = optionalClient.get();
            Assignment assignment = optionalAssignment.get();
            assignment.setClient(client);
            assignmentRepository.save(assignment);
        }
    }


    public static void assignExecutorToClient(String id, String executorId) {

        Optional<Client> optionalClient = clientRepository.findById(id);
        Optional<Executor> optionalExecutor = executorRepository.findById(executorId);

        if (optionalExecutor.isEmpty()) {
            throw new RecordNotFoundException("Executor with username id: " + executorId + " not found");
        } else if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Client with username id: " + id + " not found");

        } else {
            Client client = optionalClient.get();
            Executor executor = optionalExecutor.get();
            client.setExecutor(executor);
            clientRepository.save(client);
        }
    }


    public static void assignFileToClient(String fileName, String username) {

        Optional<Client> optionalClient = clientRepository.findById(username);
        Optional<FileDocument> optionalFile = Optional.ofNullable(docFileRepository.findByFileName(fileName));

        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Client with id: " + username + " not found");
        } else if (optionalFile.isEmpty()) {
            throw new RecordNotFoundException("File with id: " + fileName + " not found");
        } else {
            FileDocument file = optionalFile.get();
            Client client = optionalClient.get();
            file.setClient(client);
            docFileRepository.save(file);
        }
    }


    public ClientOutputDto transferToClientDto(Client client) {
        ClientOutputDto outputDto = new ClientOutputDto();

        outputDto.setUsername(client.getUsername());
        outputDto.setFirstname(client.getFirstname());
        outputDto.setLastname(client.getLastname());
        outputDto.setMobile(client.getMobile());
        outputDto.setAdres(client.getAdres());
        outputDto.setPlace(client.getPlace());
        outputDto.setZipcode(client.getZipcode());
        outputDto.setEmail(client.getEmail());
        outputDto.setStory(client.getStory());
        outputDto.setAssignments(client.getAssignments());
        outputDto.setUser(client.getUser());
        outputDto.setFileDocument(client.getFileDocument());
        outputDto.setExecutor(client.getExecutor());
        return outputDto;
    }
}
