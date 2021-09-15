package com.example.blogapi.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
@Entity
@Data
public class Connection extends BaseModel{
    @ManyToOne(cascade = CascadeType.ALL)
    private User connection;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
}
