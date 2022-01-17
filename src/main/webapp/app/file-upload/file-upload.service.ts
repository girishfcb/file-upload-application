import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { FileContent } from 'app/entities/file-content';
import { Observable } from 'rxjs';
import { FileUploadComponent } from './file-upload.component';
import { ImportResult } from './import-result';

@Injectable({
  providedIn: 'root',
})
export class FileUploadService {
  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  submitFile(memberDataFile: File): Observable<ImportResult> {
    const formData: FormData = new FormData();
    formData.append('memberDataFile', memberDataFile, memberDataFile.name);
    return this.http.post<ImportResult>(this.applicationConfigService.getEndpointFor('api/uploadFile'), formData);
  }

  downloadFile(): string {
    return this.applicationConfigService.getEndpointFor('/api/downloadContentFile');
  }

  getAllFileRecords(): Observable<FileContent[]> {
    return this.http.get<FileContent[]>(this.applicationConfigService.getEndpointFor('api/getAllContent'));
  }
}
