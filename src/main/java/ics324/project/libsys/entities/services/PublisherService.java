package ics324.project.libsys.entities.services;

import ics324.project.libsys.entities.Library;
import ics324.project.libsys.entities.Publisher;
import ics324.project.libsys.repo.LibraryRepository;
import ics324.project.libsys.repo.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    private PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository){this.publisherRepository = publisherRepository;}

    public List<Publisher> getAllPublishers() { return publisherRepository.findAll(); }

    public void savePublisher(Publisher publisher) { publisherRepository.save(publisher); }

    public void deletePublisher(Publisher publisher) { publisherRepository.delete(publisher); }

    public void save(Publisher publisher) {
        publisherRepository.save(publisher);
    }
}
