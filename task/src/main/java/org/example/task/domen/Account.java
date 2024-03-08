package org.example.task.domen;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public final class Account {
    @Id
    @Column(name = "account_id")
    private Long id;
    private String publicAccountId;
    private String email;
    private String[] role;

    public Long id() {
        return id;
    }

    public String publicAccountId() {
        return publicAccountId;
    }

    public String email() {
        return email;
    }

    public String[] role() {
        return role;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Account) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.publicAccountId, that.publicAccountId) &&
                Objects.equals(this.email, that.email) &&
                Objects.equals(this.role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, publicAccountId, email, role);
    }

    @Override
    public String toString() {
        return "Account[" +
                "id=" + id + ", " +
                "publicAccountId=" + publicAccountId + ", " +
                "email=" + email + ", " +
                "role=" + role + ']';
    }

}
