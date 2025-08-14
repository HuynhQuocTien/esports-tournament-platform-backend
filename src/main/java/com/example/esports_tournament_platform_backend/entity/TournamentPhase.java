package com.example.esports_tournament_platform_backend.entity;

import com.example.esports_tournament_platform_backend.enums.TournamentFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "tournament_phases")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TournamentPhase {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    private String phaseName; // Ví dụ: "Vòng bảng", "Playoff"

    @Enumerated(EnumType.STRING)
    private TournamentFormat format;

    private Integer roundCount; // Số vòng (cho vòng tròn / Thụy Sỹ)

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
}

