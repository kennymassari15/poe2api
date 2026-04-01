import { CommonModule } from "@angular/common";
import { HttpClient } from "@angular/common/http";
import { Component, OnInit, inject } from "@angular/core";
import { catchError, finalize, of, retry, timer } from "rxjs";

interface Character {
  id: number;
  name: string;
  image: string;
  ascendancy1: string | null;
  ascendancy2: string | null;
  ascendancy3: string | null;
}

@Component({
  selector: "app-root",
  standalone: true,
  imports: [CommonModule],
  templateUrl: "./app.component.html",
  styleUrl: "./app.component.css"
})
export class AppComponent implements OnInit {
  private readonly http = inject(HttpClient);
  private readonly maxStartupRetries = 15;

  characters: Character[] = [];
  openId: number | null = null;
  isLoading = true;
  errorMessage = "";

  get selectedCharacter(): Character | null {
    return this.characters.find((character) => character.id === this.openId) ?? null;
  }

  ngOnInit(): void {
    this.http
      .get<Character[]>("/api/characters")
      .pipe(
        retry({
          count: this.maxStartupRetries,
          delay: () => timer(1000)
        }),
        catchError((error) => {
          console.error("Failed to load characters", error);
          this.errorMessage = "Couldn't load characters from the API after waiting for the backend to start.";
          return of([]);
        }),
        finalize(() => {
          this.isLoading = false;
        })
      )
      .subscribe((characters) => {
        this.characters = characters;
      });
  }

  getAscendancies(character: Character): string[] {
    return [character.ascendancy1, character.ascendancy2, character.ascendancy3].filter(
      (value): value is string => Boolean(value && value.trim().toUpperCase() !== "N/A")
    );
  }

  toggleCharacter(id: number): void {
    this.openId = this.openId === id ? null : id;
  }

  closeModal(): void {
    this.openId = null;
  }

  trackByCharacterId(_index: number, character: Character): number {
    return character.id;
  }
}
