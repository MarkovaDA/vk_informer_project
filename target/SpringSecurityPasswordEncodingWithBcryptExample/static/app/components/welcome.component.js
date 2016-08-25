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
var _this = this;
var core_1 = require('@angular/core');
var pet_service_1 = require('../services/pet.service');
var currentuser_service_1 = require('../services/currentuser.service');
var user_model_1 = require('../models/user.model');
var AppComponent = (function () {
    function AppComponent(petService, currentUserService) {
        this.petService = petService;
        this.currentUserService = currentUserService;
        this.current = new user_model_1.User();
    }
    AppComponent.prototype.getPets = function () {
        var _this = this;
        this.petService
            .getPets()
            .then(function (pets) { return _this.pets = pets; })
            .catch(function (error) { return _this.error = error; });
    };
    AppComponent = __decorate([
        core_1.Component({
            selector: 'pets',
            templateUrl: 'app/templates/pets.template.html',
        }), 
        __metadata('design:paramtypes', [pet_service_1.PetService, currentuser_service_1.CurrentUserService])
    ], AppComponent);
    return AppComponent;
}());
exports.AppComponent = AppComponent;
getCurrentUser();
void {
    this: .currentUserService
        .getCurrentUser()
        .then(function (currentUser) { return _this.current = currentUser; })
        .catch(function (error) { return _this.error = error; })
};
ngOnInit();
void {
    this: .getPets(),
    this: .getCurrentUser() };
//# sourceMappingURL=welcome.component.js.map