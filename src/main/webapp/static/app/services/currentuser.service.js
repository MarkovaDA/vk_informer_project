"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var http_1 = require('@angular/http');
require('rxjs/add/operator/toPromise');
var CurrentUserService = (function () {
    function CurrentUserService(http) {
        this.http = http;
        this.userUrl = 'http://localhost:8084/care_your_pet/client/get_currentuser';
    }
    CurrentUserService.prototype.getCurrentUser = function () {
        return this.http.get(this.userUrl)
            .toPromise()
            .then(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    CurrentUserService.prototype.handleError = function (error) {
        console.error(error);
    };
    CurrentUserService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], CurrentUserService);
    return CurrentUserService;
}());
exports.CurrentUserService = CurrentUserService;
//# sourceMappingURL=currentuser.service.js.map